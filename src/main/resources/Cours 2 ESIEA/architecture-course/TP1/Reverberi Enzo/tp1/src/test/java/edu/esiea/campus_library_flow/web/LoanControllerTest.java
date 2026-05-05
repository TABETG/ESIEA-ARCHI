package edu.esiea.campus_library_flow.web;

import edu.esiea.campus_library_flow.application.LoanApplicationService;
import edu.esiea.campus_library_flow.web.dto.LoanResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoanApplicationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_loan() throws Exception {

        String json = """
    {
      "studentId": "1",
      "bookId": 1
    }
    """;

        LoanResponseDto response = new LoanResponseDto(
                1L, "1", 1L, LocalDate.now(), null, false
        );

        when(service.createLoan(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/loans")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value("1"));
    }

    @Test
    void should_fail_when_invalid_input() throws Exception {

        String json = """
    {
      "studentId": "",
      "bookId": -1
    }
    """;

        mockMvc.perform(post("/api/v1/loans")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_loan_by_id() throws Exception {

        LoanResponseDto response = new LoanResponseDto(
                1L, "1", 1L, LocalDate.now(), null, false
        );

        when(service.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/loans/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }


    @Test
    void should_get_loans_by_student() throws Exception {

        when(service.findByStudentId(1L))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/loans/student/1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_close_loan() throws Exception {

        LoanResponseDto response = new LoanResponseDto(
                1L, "1", 1L, LocalDate.now(), LocalDate.now(), true
        );

        when(service.closeLoan(1L)).thenReturn(response);

        mockMvc.perform(post("/api/v1/loans/close/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.returned").value(true));
    }

}
