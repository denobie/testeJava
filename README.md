# <a name="_801axu584fmg"></a>Objetivo
Criar uma API Rest para cadastrar e manipular débitos
# <a name="_rdmq4dgbl9d5"></a>Requisitos Funcionais
1. Buscar todos os débitos
   1. Paginação e possibilidade de filtrar por data de lançamento, cpf e nome da pessoa
1. Buscar um débito por id
1. Buscar o valor total de todos os débitos lançados
1. Criar um débito
   1. Campos obrigatórios
      1. data de lançamento
      1. cpf da pessoa
      1. nome da pessoa
      1. parcelas
         1. número 
         1. data de vencimento
         1. valor
   1. Validações:
      1. Deve ter pelo menos uma parcela
      1. A data de lançamento deve ser menor ou igual a data atual
      1. O número da parcela deve ser único por débito
      1. A data de vencimento da parcela deve ser maior do que a data atual
      1. O valor da parcela deve ser maior que 0
      1. O CPF da pessoa deve ser válido
1. Incluir uma parcela nova em um débito já existente
1. Alterar a data de vencimento de uma parcela
1. Excluir um débito

# <a name="_71ubdbqxnw7v"></a>Requisitos Técnicos
- Java e spring boot
- Salvar em algum banco de dados relacional
- Testes unitários e de integração
# <a name="_rjtqb2lkx9zz"></a>Extras
## <a name="_m7rvsiyxcqnz"></a>Funcionalidades
1. Movimentações das parcelas
   1. Pagamento 
   1. Cancelamento
1. Extrato de débitos por pessoa
1. Extrato de débitos por situação da parcela
## <a name="_nwrknp30p101"></a>Técnicos
1. Front-end usando react
1. Documentação
1. Docker
1. Publicação da aplicação em algum provedor (AWS, Google Cloud ou Azure)
1. Pipeline que roda testes e faz o build
