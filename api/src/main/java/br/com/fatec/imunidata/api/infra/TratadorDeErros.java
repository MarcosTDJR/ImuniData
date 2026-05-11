package br.com.fatec.imunidata.api.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErroCustomizada> tratarErro400(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        List<DadosErroValidacao> listaDeErros = erros.stream()
                .map(DadosErroValidacao::new)
                .collect(Collectors.toList());

        var resposta = new RespostaErroCustomizada(
                HttpStatus.BAD_REQUEST.value(),
                listaDeErros,
                "Verifique se todos os campos obrigatórios foram preenchidos corretamente e tente novamente."
        );

        return ResponseEntity.badRequest().body(resposta);
    }

    private record RespostaErroCustomizada(int status_code, List<DadosErroValidacao> errors, String message) {}

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
