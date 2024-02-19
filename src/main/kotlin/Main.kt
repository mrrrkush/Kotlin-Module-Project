data class Note(val title: String, val content: String)

fun main() {
    val menu = Menu()

    while (true) {
        println("Меню:")
        println("1. Архивы")
        println("0. Выйти")

        when (readlnOrNull()) {
            "1" -> menu.showArchivesMenu()
            "0" -> return
            else -> println("Ошибка: Введите корректное значение")
        }
    }
}
