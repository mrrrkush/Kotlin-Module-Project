data class Note(val title: String, val content: String)

class Archive(val name: String) {
    private val notes = mutableListOf<Note>()

    fun addNote(title: String, content: String) {
        if (title.isNotBlank() && content.isNotBlank()) {
            notes.add(Note(title, content))
            println("Заметка добавлена в архив $name")
        } else {
            println(": Заголовок и содержание заметки не могут быть пустыми!")
        }
    }

    fun viewNotes() {
        if (notes.isNotEmpty()) {
            println("Заметки в архиве $name:")
            notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.title}")
            }

            println("Выберите заметку для просмотра содержимого (или 0 для выхода):")
            val choice = readlnOrNull()
            if (choice == "0") return
            val noteIndex = choice?.toIntOrNull()?.minus(1) ?: -1

            if (noteIndex in notes.indices) {
                viewNoteContent(noteIndex)
            } else {
                println("Ошибка: Некорректный выбор заметки")
            }
        } else {
            println("В архиве $name нет заметок")
        }
    }
    private fun viewNoteContent(index: Int) {
        if (index in notes.indices) {
            val note = notes[index]
            println("Заголовок: ${note.title}")
            println("Содержание: ${note.content}")
        } else {
            println("Ошибка: Некорректный индекс заметки")
        }
    }
}

class Menu {
    private val archives = mutableListOf<Archive>()
    private var currentArchive: Archive? = null

    fun showArchivesMenu() {
        println("Выберите действие:")
        println("1. Создать архив")
        println("2. Просмотреть архивы")
        println("0. Выйти")

        when (readlnOrNull()) {
            "1" -> createArchive()
            "2" -> viewArchives()
            "0" -> return
            else -> {
                println("Ошибка: Введите корректное значение")
                showArchivesMenu()
            }
        }
    }

    private fun createArchive() {
        println("Введите название архива:")
        val archiveName = readlnOrNull() ?: ""
        archives.add(Archive(archiveName))
        println("Архив $archiveName создан")
    }

    private fun viewArchives() {
        if (archives.isNotEmpty()) {
            println("Доступные архивы:")
            archives.forEachIndexed { index, archive ->
                println("${index + 1}. ${archive.name}")
            }

            println("Выберите архив для просмотра (или 0 для выхода):")
            val choice = readlnOrNull()
            if (choice == "0") return
            val archiveIndex = choice?.toIntOrNull()?.minus(1) ?: -1

            if (archiveIndex in archives.indices) {
                currentArchive = archives[archiveIndex]
                showArchiveMenu()
            } else {
                println("Ошибка: Некорректный выбор архива")
                viewArchives()
            }
        } else {
            println("Нет доступных архивов")
        }
    }

    private fun showArchiveMenu() {
        println("Выберите действие:")
        println("1. Добавить заметку")
        println("2. Просмотреть заметки")
        println("0. Выйти в список архивов")

        when (readlnOrNull()) {
            "1" -> createNote()
            "2" -> viewNotes()
            "0" -> {
                currentArchive = null
                viewArchives()
            }
            else -> {
                println("Ошибка: Введите корректное значение")
                showArchiveMenu()
            }
        }
    }

    private fun createNote() {
        println("Введите заголовок заметки:")
        val title = readlnOrNull() ?: ""
        println("Введите содержание заметки:")
        val content = readlnOrNull() ?: ""
        currentArchive?.addNote(title, content)
    }

    private fun viewNotes() {
        currentArchive?.viewNotes() ?: println("Ошибка! Нет выбранного архива")
        showArchiveMenu()
    }
}

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
