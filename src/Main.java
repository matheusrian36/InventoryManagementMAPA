import Modelos.Products;

import java.util.Scanner;

public class Main {

    private Products listProducts[] = new Products[5];
    private int currentPosition = 0;


    public static void main(String[] args) {

        Main main = new Main();
        int opcao = 0;
        do {
            opcao = main.getStdMenuChoice();
            switch (opcao){
                case 1:
                    main.registerProductMenu();
                    break;
                case 2:
                    main.productFlowMenu();
                    break;
                case 3:
                    main.priceAdjustment();
                    break;
                case 4:
                    main.printReport();
                    break;
                case 0:
                    System.out.println("THE APPLICATION WILL END");
                    break;
                default:
                    main.invalidOption();
                    break;
            }
        }while (opcao != 0);

    }

    private int getStdMenuChoice(){
        int choice;
        showHeader();
        System.out.println("MENU PRINCIPAL \n" +
                "1 - CADASTRO DE PRODUTOS \n" +
                "2 - MOVIMENTAÇÃO \n" +
                "3 - REAJUSTE DE PREÇOS \n" +
                "4 - RELATÓRIOS \n" +
                "0 - FINALIZAR \n"
        );
        choice = getChoice();
        return choice;
    }

    private void registerProductMenu(){
        int choice;
        do {
            showHeader();
            System.out.println("CADASTRO DE PRODUTOS \n" +
                    "1 - INCLUSÃO \n" +
                    "2 - ALTERAÇÃO \n" +
                    "3 - CONSULTA \n" +
                    "4 - EXCLUSÃO \n" +
                    "0 - RETORNAR \n"
            );
            choice = getChoice();
            switch (choice){
                case 1:
                    includeProduct();
                    break;
                case 2:
                    alterProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    excludeProduct();
                    break;
                case 0:
                    break;
                default:
                    invalidOption();
                    break;
            }
        }while(choice!=0);
    }

    private void excludeProduct(){
        String choice;
        Scanner scanner = new Scanner(System.in);
        do {
            showHeader();
            System.out.println("CADASTRO DE PRODUTOS \n" +
                    "EXCLUSÃO DE PRODUTO \n");
            System.out.println("Informe o nome do produto a ser excluído: \n");
            String queryName = scanner.nextLine();
            boolean control = true;
            for(int i = 0; i < currentPosition; i++){
                if(queryName.equalsIgnoreCase(listProducts[i].getNome())){
                    control = false;
                    System.out.println("Produto localizado! \n");
                    System.out.println("\nNome: " + listProducts[i].getNome() +
                            "\nUnidade: " + listProducts[i].getUnidade() +
                            "\nPreço unitário: R$" + listProducts[i].getPrecoUnitario() +
                            "\nQuantidade em estoque: " + listProducts[i].getQuantidadeEstoque());
                    choice = confirmOperation();
                    if(choice.equalsIgnoreCase("S")){
                        for(int j = i; j < currentPosition - 1; j++){
                            listProducts[j] = listProducts[j + 1];
                        }
                    currentPosition--;
                    break;
                    }
                }
            }
            msgQueryNotFound(control);
            choice = repeatOperation();
        }while (choice.equalsIgnoreCase("S"));
    }

    private void searchProduct(){
        String choice;
        Scanner scanner = new Scanner(System.in);
        do {
            showHeader();
            System.out.println("CADASTRO DE PRODUTOS \n" +
                    "CONSULTA DE PRODUTO \n");
            System.out.println("Informe o nome do produto a ser consultado: \n");
            String queryName = scanner.nextLine();
            boolean control = true;
            for(int i = 0; i < currentPosition; i++){
                if(queryName.equalsIgnoreCase(listProducts[i].getNome())){
                    control = false;
                    System.out.println("Produto localizado! \n");
                    System.out.println("\nNome: " + listProducts[i].getNome() +
                            "\nUnidade: " + listProducts[i].getUnidade() +
                            "\nPreço unitário: R$" + listProducts[i].getPrecoUnitario() +
                            "\nQuantidade em estoque: " + listProducts[i].getQuantidadeEstoque());
                    break;
                }
            }
            msgQueryNotFound(control);
            choice = repeatOperation();
        }while (choice.equalsIgnoreCase("S"));
    }

    private void alterProduct(){
        String choice;
        Scanner scanner = new Scanner(System.in);
        do {
            showHeader();
            System.out.println("CADASTRO DE PRODUTOS \n" +
                    "ALTERAÇÃO DE PRODUTO \n");
            System.out.println("Informe o nome do produto a ser alterado: \n");
            String queryName = scanner.nextLine();
            boolean control = true;
            for(int i = 0; i < currentPosition; i++){
                if(queryName.equalsIgnoreCase(listProducts[i].getNome())){
                    control = false;
                    Products products = new Products();
                    System.out.println("Informe a unidade de medida para o produto: ");
                    String unidade = scanner.nextLine();
                    System.out.println("Informe o preço por unidade: ");
                    double preco = scanner.nextDouble();
                    if(preco <= 0){
                        System.out.println("Preço inválido! Tente novamente");
                        System.out.println("Informe o preço por unidade: ");
                        preco = scanner.nextDouble();
                    }
                    System.out.println("Informe a quantidade em estoque: ");
                    int quantidade = scanner.nextInt();
                    if(quantidade < 0){
                        System.out.println("Quantidade inválida! Tente novamente");
                        System.out.println("Informe a quantidade em estoque: ");
                        quantidade = scanner.nextInt();
                    }
                    products.setNome(listProducts[i].getNome());
                    products.setUnidade(unidade);
                    products.setPrecoUnitario(preco);
                    products.setQuantidadeEstoque(quantidade);
                    choice = confirmOperation();
                    if(choice.equalsIgnoreCase("S")){
                        listProducts[i] = products;
                    }
                    break;
                }
            }
            msgQueryNotFound(control);
            choice = repeatOperation();
        }while (choice.equalsIgnoreCase("S"));
    }

    private void includeProduct(){
        String choice;
        do {
            showHeader();
            System.out.println("CADASTRO DE PRODUTOS \n" +
                    "INCLUSÃO DE PRODUTO \n");
            Products products = setProductData();
            choice = confirmOperation();
            if(choice.equalsIgnoreCase("S")){
                listProducts[currentPosition] = products;
                currentPosition++;
            }
            choice = repeatOperation();
        }while(choice.equalsIgnoreCase("S"));
    }

    private Products setProductData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do produto: ");
        String nome = scanner.nextLine();
        for(int i = 0; i < currentPosition; i++){
            if(nome.equalsIgnoreCase(listProducts[i].getNome())){
                System.out.println("Já existe produto cadastrado de mesmo nome! Tente novamente");
                System.out.println("Informe o nome do produto: ");
                nome = scanner.nextLine();
                break;
            }
        }
        System.out.println("Informe a unidade de medida para o produto: ");
        String unidade = scanner.nextLine();
        System.out.println("Informe o preço por unidade: ");
        double preco = scanner.nextDouble();
        if(preco <= 0){
            System.out.println("Preço inválido! Tente novamente");
            System.out.println("Informe o preço por unidade: ");
            preco = scanner.nextDouble();
        }
        System.out.println("Informe a quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        if(quantidade < 0){
            System.out.println("Quantidade inválida! Tente novamente");
            System.out.println("Informe a quantidade em estoque: ");
            quantidade = scanner.nextInt();
        }

        Products products = new Products();
        products.setNome(nome);
        products.setUnidade(unidade);
        products.setPrecoUnitario(preco);
        products.setQuantidadeEstoque(quantidade);
        return products;
    }

    private void productFlowMenu(){
        int choice;
        do {
            showHeader();
            System.out.println("MOVIMENTAÇÃO DE PRODUTOS \n" +
                    "1 - ENTRADA \n" +
                    "2 - SAÍDA \n" +
                    "0 - RETORNAR \n"
            );
            choice = getChoice();
            switch (choice){
                case 1:
                    inputProduct();
                    break;
                case 2:
                    outputProduct();
                    break;
                case 0:
                    break;
                default:
                    invalidOption();
                    break;
            }
        }while(choice!=0);
    }

    private void inputProduct(){
        String choice;
        int inputAmount;
        int finalAmount;
        Scanner scanner = new Scanner(System.in);
        do {
            showHeader();
            System.out.println("MOVIMENTAÇÃO DE PRODUTOS \n" +
                    "ENTRADA DE PRODUTO \n");
            System.out.println("Informe o nome do produto a ser atualizado: \n");
            String queryName = scanner.nextLine();
            boolean controle = true;
            for(int i = 0; i < currentPosition; i++){
                if(queryName.equalsIgnoreCase(listProducts[i].getNome())){
                    controle = false;
                    System.out.println("Produto localizado! \n");
                    System.out.println("\nNome: " + listProducts[i].getNome() +
                            "\nUnidade: " + listProducts[i].getUnidade() +
                            "\nPreço unitário: R$" + listProducts[i].getPrecoUnitario() +
                            "\nQuantidade em estoque: " + listProducts[i].getQuantidadeEstoque());
                    System.out.println("Informe a quantidade a ser adicionada em estoque: ");
                    inputAmount = scanner.nextInt();
                    finalAmount = listProducts[i].getQuantidadeEstoque() + inputAmount;
                    System.out.println("Nova quantidade em estoque: " + finalAmount);
                    choice = confirmOperation();
                    if(choice.equalsIgnoreCase("S")){
                        listProducts[i].setQuantidadeEstoque(finalAmount);
                        break;
                    }
                }
            }
            msgQueryNotFound(controle);
            choice = repeatOperation();
        }while (choice.equalsIgnoreCase("S"));
    }

    private void outputProduct(){
        String choice;
        int outputAmount;
        int finalAmount;
        Scanner scanner = new Scanner(System.in);
        do {
            showHeader();
            System.out.println("MOVIMENTAÇÃO DE PRODUTOS \n" +
                    "SAÍDA DE PRODUTO \n");
            System.out.println("Informe o nome do produto a ser atualizado: \n");
            String queryName = scanner.nextLine();
            boolean controle = true;
            for(int i = 0; i < currentPosition; i++){
                if(queryName.equalsIgnoreCase(listProducts[i].getNome())){
                    controle = false;
                    System.out.println("Produto localizado! \n");
                    System.out.println("\nNome: " + listProducts[i].getNome() +
                            "\nUnidade: " + listProducts[i].getUnidade() +
                            "\nPreço unitário: R$" + listProducts[i].getPrecoUnitario() +
                            "\nQuantidade em estoque: " + listProducts[i].getQuantidadeEstoque());
                    System.out.println("Informe a quantidade a ser subtraída em estoque: ");
                    outputAmount = scanner.nextInt();
                    finalAmount = listProducts[i].getQuantidadeEstoque() - outputAmount;
                    System.out.println("Nova quantidade em estoque: " + finalAmount);
                    choice = confirmOperation();
                    if(choice.equalsIgnoreCase("S")){
                        listProducts[i].setQuantidadeEstoque(finalAmount);
                        break;
                    }
                }
            }
            msgQueryNotFound(controle);
            choice = repeatOperation();
        }while (choice.equalsIgnoreCase("S"));
    }

    private void priceAdjustment(){
        String choice;
        int modal;
        double percent;
        double finalPrice;
        do {
            Scanner scanner = new Scanner(System.in);
            showHeader();
            System.out.println("REAJUSTE DE PREÇO \n");
            System.out.println("Informe o nome do produto a ser atualizado: \n");
            String queryName = scanner.nextLine();
            boolean control = true;
            for(int i = 0; i < currentPosition; i++){
                if(queryName.equalsIgnoreCase(listProducts[i].getNome())){
                    control = false;
                    System.out.println("\nProduto localizado!");
                    System.out.println("\nNome: " + listProducts[i].getNome() +
                            "\nUnidade: " + listProducts[i].getUnidade() +
                            "\nPreço unitário: R$" + listProducts[i].getPrecoUnitario() +
                            "\nQuantidade em estoque: " + listProducts[i].getQuantidadeEstoque());

                    System.out.println("\n1 - Aumento de preço" +
                            "\n2 - Diminuição de preço");
                    modal = getChoice();
                    if(modal == 1){
                        System.out.println("Informe a variação de preço a ser aplicada em porcentagem: ");
                        percent = (scanner.nextDouble())/100;
                        finalPrice = listProducts[i].getPrecoUnitario() + (listProducts[i].getPrecoUnitario() * percent);
                        System.out.println("Novo preço unitário: " + finalPrice);
                        choice = confirmOperation();
                        if(choice.equalsIgnoreCase("S")){
                            listProducts[i].setPrecoUnitario(finalPrice);
                            break;
                        }
                    }
                    else if(modal == 2){
                        System.out.println("Informe a variação de preço a ser aplicada em porcentagem: ");
                        percent = (scanner.nextDouble())/100;
                        finalPrice = listProducts[i].getPrecoUnitario() - (listProducts[i].getPrecoUnitario() * percent);
                        System.out.println("Novo preço unitário: " + finalPrice);
                        choice = confirmOperation();
                        if(choice.equalsIgnoreCase("S")){
                            listProducts[i].setPrecoUnitario(finalPrice);
                            break;
                        }
                    }
                }
            }
            msgQueryNotFound(control);
            choice = repeatOperation();
        }while (choice.equalsIgnoreCase("S"));
    }

    private void printReport(){
        showHeader();
        System.out.println("RELATÓRIO: ");
        for(int i = 0; i < currentPosition; i++){
            System.out.println("\n");
            System.out.println("\nNome: " + listProducts[i].getNome() +
                    "\nUnidade: " + listProducts[i].getUnidade() +
                    "\nPreço unitário: R$" + listProducts[i].getPrecoUnitario() +
                    "\nQuantidade em estoque: " + listProducts[i].getQuantidadeEstoque());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("DIGITE QUALQUER LETRA E APERTE ENTER PARA CONTINUAR");
        scanner.next();
    }

    private String repeatOperation(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        choice = scanner.next();
        return choice;
    }

    private String confirmOperation(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        choice = scanner.next();
        return choice;
    }

    private void msgQueryNotFound(boolean control) {
        if (control) {
            System.out.println("Produto não encontrado");
        }
    }

    private int getChoice(){
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }

    private void showHeader(){
        System.out.println("EMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA. \n");
        System.out.println("SISTEMA DE CONTROLE DE ESTOQUE \n");
    }

    private void invalidOption(){
        System.out.println("Opção Inválida!");
    }
}
