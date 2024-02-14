Objetivo
Criar uma API Rest para cadastrar e manipular débitos
Requisitos Funcionais
Buscar todos os débitos
Paginação e possibilidade de filtrar por data de lançamento, cpf e nome da pessoa
Buscar um débito por id
Buscar o valor total de todos os débitos lançados
Criar um débito
Campos obrigatórios
data de lançamento
cpf da pessoa
nome da pessoa
parcelas
número 
data de vencimento
valor
Validações:
Deve ter pelo menos uma parcela
A data de lançamento deve ser menor ou igual a data atual
O número da parcela deve ser único por débito
A data de vencimento da parcela deve ser maior do que a data atual
O valor da parcela deve ser maior que 0
O CPF da pessoa deve ser válido
Incluir uma parcela nova em um débito já existente
Alterar a data de vencimento de uma parcela
Excluir um débito

Requisitos Técnicos
Java e spring boot
Salvar em algum banco de dados relacional
Testes unitários e de integração
Extras
Funcionalidades
Movimentações das parcelas
Pagamento 
Cancelamento
Extrato de débitos por pessoa
Extrato de débitos por situação da parcela
Técnicos
Front-end usando react
Documentação
Docker
Publicação da aplicação em algum provedor (AWS, Google Cloud ou Azure)
Pipeline que roda testes e faz o build
