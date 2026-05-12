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

    public static class RespostaErroCustomizada {
        private int status_code;
        private List<DadosErroValidacao> errors;
        private String message;

        public RespostaErroCustomizada(int status_code, List<DadosErroValidacao> errors, String message) {
            this.status_code = status_code;
            this.errors = errors;
            this.message = message;
        }

        public int getStatus_code() {
            return status_code;
        }

        public void setStatus_code(int status_code) {
            this.status_code = status_code;
        }

        public List<DadosErroValidacao> getErrors() {
            return errors;
        }

        public void setErrors(List<DadosErroValidacao> errors) {
            this.errors = errors;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DadosErroValidacao {
        private String campo;
        private String mensagem;

        public DadosErroValidacao(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }

        public String getCampo() {
            return campo;
        }

        public void setCampo(String campo) {
            this.campo = campo;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}
