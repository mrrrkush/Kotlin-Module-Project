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
                println("Ошибка! Некорректный выбор заметки")
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
            println("Ошибка! Некорректный индекс заметки")
        }
    }
}