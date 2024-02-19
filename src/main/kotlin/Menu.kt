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
                println("Ошибка! Введите корректное значение")
                showArchivesMenu()
            }
        }
    }

    private fun createArchive() {
        println("Введите имя нового архива:")
        var archiveName = readlnOrNull()?.trim()

        while (archiveName.isNullOrBlank()) {
            println("Ошибка! Имя архива не может быть пустым. Пожалуйста, введите имя заново:")
            archiveName = readlnOrNull()?.trim()
        }

        val newArchive = Archive(archiveName)
        archives.add(newArchive)
        println("Архив \"$archiveName\" успешно создан")
        viewArchives()
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
                println("Ошибка! Некорректный выбор архива")
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
                println("Ошибка! Введите корректное значение")
                showArchiveMenu()
            }
        }
    }

    private fun createNote() {
        println("Введите заголовок заметки:")
        var title = readlnOrNull()?.trim()

        while (title.isNullOrBlank()) {
            println("Ошибка! Заголовок заметки не может быть пустым. Пожалуйста, введите заголовок заново:")
            title = readlnOrNull()?.trim()
        }

        println("Введите содержание заметки:")
        var content = readlnOrNull()?.trim()

        while (content.isNullOrBlank()) {
            println("Ошибка! Содержание заметки не может быть пустым. Пожалуйста, введите содержание заново:")
            content = readlnOrNull()?.trim()
        }

        currentArchive?.addNote(title, content)
    }

    private fun viewNotes() {
        currentArchive?.viewNotes() ?: println("Ошибка! Нет выбранного архива")
        showArchiveMenu()
    }
}