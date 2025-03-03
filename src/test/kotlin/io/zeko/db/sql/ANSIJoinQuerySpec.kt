package io.zeko.db.sql

import io.zeko.db.sql.aggregations.count
import io.zeko.db.sql.aggregations.sumGt
import io.zeko.db.sql.dsl.*
import io.zeko.db.sql.operators.isNotNull
import kotlin.test.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class ANSIJoinQuerySpec :
    Spek({
        fun debug(msg: Any) {
            if (false) println(msg.toString())
        }

        describe("Query Builder DSL Join Queries ANSI test") {
            context("Given multiple tables join(user has address, and has many roles)") {
                it("should match sql with one table left join") {
                    val sql =
                        ANSIQuery()
                            .fields("*")
                            .from("user")
                            .leftJoin("address")
                            .on("user_id = user.id")
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """SELECT * FROM "user" LEFT JOIN "address" ON ("address".user_id = "user".id )""",
                        sql
                    )
                }

                it("should match sql with one table inner join with field rename") {
                    val sql =
                        ANSIQuery()
                            .fields(
                                "user.id AS user_id",
                                "name",
                                "street1",
                                "street2",
                                "address.id AS address_id"
                            )
                            .from("user")
                            .innerJoin("address")
                            .on("user_id = user.id")
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """SELECT user.id AS user_id, name, street1, street2, address.id AS address_id FROM "user" INNER JOIN "address" ON ("address".user_id = "user".id )""",
                        sql
                    )
                }

                it(
                    "should match sql with one table left join with field selection and where conditions"
                ) {
                    val sql =
                        ANSIQuery()
                            .table("user")
                            .fields("id", "name")
                            .table("address")
                            .fields("id", "street1", "street2", "user.id = user_id")
                            .from("user")
                            .leftJoin("address")
                            .on("user_id = user.id")
                            .where("user.status" greater 0 or isNotNull("user.id"))
                            .orderDesc("user.name")
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """SELECT "user".id AS "user-id", "user".name AS "user-name", "address".id AS "address-id", "address".street1 AS "address-street1", "address".street2 AS "address-street2", "user".id AS "address-user_id" FROM "user" LEFT JOIN "address" ON ("address".user_id = "user".id ) WHERE "user".status > 0 OR "user".id IS NOT NULL ORDER BY "user".name DESC""",
                        sql
                    )
                }

                it("should match sql with three table joins") {
                    val sql =
                        ANSIQuery()
                            .table("user")
                            .fields("id", "name")
                            .table("role")
                            .fields("id", "role_name", "user.id = user_id")
                            .table("address")
                            .fields("id", "street1", "street2", "user.id = user_id")
                            .from("user")
                            .leftJoin("address")
                            .on("user_id = user.id")
                            .leftJoin("user_has_role")
                            .on("user_id = user.id")
                            .leftJoin("role")
                            .on("id = user_has_role.role_id")
                            .where(
                                "user.status" greater 0 or ("user.id" notInList arrayOf(1, 2, 3))
                            )
                            .order("user.id")
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """SELECT "user".id AS "user-id", "user".name AS "user-name", "role".id AS "role-id", "role".role_name AS "role-role_name", "user".id AS "role-user_id", "address".id AS "address-id", "address".street1 AS "address-street1", "address".street2 AS "address-street2", "user".id AS "address-user_id" FROM "user" LEFT JOIN "address" ON ("address".user_id = "user".id ) LEFT JOIN "user_has_role" ON ("user_has_role".user_id = "user".id ) LEFT JOIN "role" ON ("role".id = "user_has_role".role_id ) WHERE "user".status > 0 OR "user".id NOT IN (1,2,3) ORDER BY "user".id ASC""",
                        sql
                    )
                }

                it("should match sql with three table joins with select from subquery") {
                    val sql =
                        ANSIQuery()
                            .table("user")
                            .fields("id", "name")
                            .table("role")
                            .fields("id", "role_name", "user.id = user_id")
                            .table("address")
                            .fields("id", "street1", "street2", "user.id = user_id")
                            .from(
                                ANSIQuery()
                                    .fields("*")
                                    .from("user")
                                    .where("id" lessEq 100 and ("age" greater 50))
                                    .order("user.id")
                                    .limit(10, 0)
                                    .asTable("user")
                            )
                            .leftJoin("address")
                            .on("user_id = user.id")
                            .leftJoin("user_has_role")
                            .on("user_id = user.id")
                            .leftJoin("role")
                            .on("id = user_has_role.role_id")
                            .where(
                                "user.status" greater 0 or ("user.id" notInList arrayOf(1, 2, 3))
                            )
                            .order("user.name")
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """
                    SELECT "user".id AS "user-id", "user".name AS "user-name", "role".id AS "role-id", 
                    "role".role_name AS "role-role_name", "user".id AS "role-user_id", "address".id AS 
                    "address-id", "address".street1 AS "address-street1", "address".street2 AS 
                    "address-street2", "user".id AS "address-user_id" FROM 
                    (SELECT * FROM "user" WHERE id <= 100 AND age > 50 ORDER BY "user".id ASC LIMIT 10 OFFSET 0) AS "user" 
                    LEFT JOIN "address" ON ("address".user_id = "user".id ) 
                    LEFT JOIN "user_has_role" ON ("user_has_role".user_id = "user".id ) 
                    LEFT JOIN "role" ON ("role".id = "user_has_role".role_id ) 
                    WHERE "user".status > 0 OR "user".id NOT IN (1,2,3) ORDER BY "user".name ASC
                """
                            .trimIndent()
                            .replace("\n", ""),
                        sql
                    )
                }

                it("should match sql with three table joins with group by and having") {
                    val sql =
                        ANSIQuery()
                            .table("user")
                            .fields("id", "name")
                            .table("role")
                            .fields("id", "role_name", "user.id = user_id")
                            .table("address")
                            .fields("id", "street1", "street2", "user.id = user_id")
                            .from("user")
                            .leftJoin("address")
                            .on("user_id = user.id")
                            .leftJoin("user_has_role")
                            .on("user_id = user.id")
                            .leftJoin("role")
                            .on("id = user_has_role.role_id")
                            .where(
                                "user.status" greater 0 or ("user.id" notInList arrayOf(1, 2, 3))
                            )
                            .groupBy("role.id", "role.name")
                            .having(sumGt("role.id", 2), count("role.id") less 10)
                            .order("user.id")
                            .limit(10, 20)
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """
                    SELECT "user".id AS "user-id", "user".name AS "user-name", "role".id AS "role-id", 
                    "role".role_name AS "role-role_name", "user".id AS "role-user_id", "address".id AS 
                    "address-id", "address".street1 AS "address-street1", "address".street2 AS 
                    "address-street2", "user".id AS "address-user_id" FROM "user" 
                    LEFT JOIN "address" ON ("address".user_id = "user".id ) 
                    LEFT JOIN "user_has_role" ON ("user_has_role".user_id = "user".id ) 
                    LEFT JOIN "role" ON ("role".id = "user_has_role".role_id ) 
                    WHERE "user".status > 0 OR "user".id NOT IN (1,2,3) 
                    GROUP BY "role".id, "role".name 
                    HAVING SUM( "role".id ) > 2 AND COUNT( "role".id ) < 10 
                    ORDER BY "user".id ASC 
                    LIMIT 10 OFFSET 20
                """
                            .trimIndent()
                            .replace("\n", ""),
                        sql
                    )
                }

                it("should match sql with one table inner join a subquery") {
                    val sql =
                        ANSIQuery()
                            .fields("*")
                            .from("user")
                            .innerJoin(
                                ANSIQuery()
                                    .fields(
                                        "id",
                                        "user_id",
                                        "(total_savings - total_spendings) AS balance"
                                    )
                                    .from("report"),
                                "user_wallet"
                            )
                            .on("user_wallet.user_id = user.id")
                            .toSql()
                    debug(sql)
                    assertEquals(
                        """
                    SELECT * FROM "user" INNER JOIN ( 
                    SELECT id, user_id, (total_savings - total_spendings) AS balance FROM "report" ) AS "user_wallet" 
                    ON ( "user_wallet".user_id = "user".id )
                """
                            .trimIndent()
                            .replace("\n", ""),
                        sql
                    )
                }
            }
        }
    })
