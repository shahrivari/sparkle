package io.github.shahrivari.sparkle

import spark.Request
import spark.Response
import spark.Service

inline fun <reified T> Request.receive(): T =
        getSparkleGson().fromJson<T>(body(), T::class.java)

fun Response.returnAsJson(a: Any): String {
    status(200)
    type("application/json; charset=utf-8")
    return getSparkleGson().toJson(a)
}

fun Service.loggyPost(path: String, handler: (req: Request, res: Response) -> Any) {
    fun captured(req: Request, res: Response): Any {
        return try {
            handler.invoke(req, res)
        } catch (t: Throwable) {
            res.returnAsJson("error" to "${t.message}")
        }
    }
    post(path, ::captured)
}
