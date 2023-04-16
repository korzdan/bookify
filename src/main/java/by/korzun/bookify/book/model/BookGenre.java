package by.korzun.bookify.book.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BookGenre {
    PROSE("Проза"),
    ADVENTURE("Приключение"),
    POETRY("Поэзия"),
    FANTASY("Фэнтези"),
    NOVEL("Роман"),
    DETECTIVE("Детектив"),
    NON_FICTION("Нехудожественная литература");

    private final String value;
}
