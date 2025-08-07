# IA RAG Application 🤖

Aplicação Java Spring Boot para ingestão de documentos PDF, divisão em chunks e armazenamento em base vetorial para uso em sistemas de Retrieval-Augmented Generation (RAG).

## Funcionalidades

- Leitura automática de arquivos PDF usando Apache Tika.
- Divisão do texto em chunks otimizados por tokens.
- Armazenamento dos chunks em um `VectorStore` para busca semântica eficiente.
- Integração pronta com modelos generativos (ex: Ollama, OpenAI).
- Endpoint REST para interação via chat utilizando RAG.

## Como funciona

1. O arquivo PDF é lido automaticamente da pasta `resources/pdf/policy.pdf`.
2. O texto extraído é dividido em segmentos menores (chunks) usando `TokenTextSplitter`, facilitando a busca e a indexação.
3. Os chunks são enviados para o `VectorStore` para indexação, permitindo buscas semânticas rápidas e precisas.
4. O usuário pode interagir com o sistema através de uma rota de chat, recebendo respostas baseadas nos documentos processados.

## Como rodar

1. **Clone o repositório:**
    ```bash
    git clone https://github.com/seuusuario/ia-rag-application.git
    cd ia-rag-application
    ```

2. **Adicione seu PDF:**  
   Coloque o arquivo que deseja processar em `src/main/resources/pdf/policy.pdf`.

3. **Certifique-se de que o Docker está rodando em sua máquina.**  
   O projeto faz uso de containers (ex: para banco vetorial com PgVector e para a engine Ollama).

4. **Execute o projeto com Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```

5. **Acompanhe o log:**  
   O progresso da ingestão e indexação do PDF será exibido no log da aplicação.

## Endpoints

- **Rota para conversar com o modelo (chat):**

    ```
    GET /chat
    ```
    **Exemplo de uso:**
    ```http
    GET /chat
    Body: "Sua pergunta sobre o conteúdo do PDF"
    ```


## Observações

- O projeto utiliza a biblioteca Ollama para geração de respostas, facilitando a execução local de modelos de linguagem.
- **Recomendação:** Para obter respostas mais precisas e avançadas, recomenda-se integrar futuramente um modelo mais potente, como o ChatGPT (OpenAI), especialmente em ambientes produtivos.

## Configuração

- O tamanho dos chunks pode ser ajustado no construtor do `TokenTextSplitter` localizado em `RagConsumerDocument.java`.
- O `VectorStore` pode ser facilmente configurado para diferentes soluções de base vetorial (FAISS, Pinecone, Redis, PgVector, etc).

## Dependências principais

- Java 21+
- Spring Boot
- spring-ai
- Apache Tika
- Docker (necessário rodar containers PgVector e Ollama)

## Estrutura do Projeto

- `service/RagConsumerDocument.java`: Serviço responsável pela ingestão e split do PDF.
- `resources/pdf/policy.pdf`: PDF a ser processado.
- `controller/ChatController.java`: Endpoint para interação via chat.

