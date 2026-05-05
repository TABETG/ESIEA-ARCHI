package edu.esiea.campus_library_flow.infrastructure.mapper;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.BookEntity;
import edu.esiea.campus_library_flow.web.dto.BookResponseDto;
import edu.esiea.campus_library_flow.web.dto.CreateBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {

    Book toDomain(BookEntity book);

    BookEntity toEntity(Book book);

    List<Book> booksToDomain(List<BookEntity> books);

}
