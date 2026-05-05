package edu.esiea.campus_library_flow.web.mapper;


import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.web.dto.BookResponseDto;
import edu.esiea.campus_library_flow.web.dto.CreateBookRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDto toDto(Book book);

    Book toEntity(CreateBookRequestDto dto);

    List<BookResponseDto> booksToDto(List<Book> books);
}
