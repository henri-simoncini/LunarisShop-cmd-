import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static long saldo;

    static String[] carrinho = new String[20];
    static int qtdCarrinho = 0;

    static void espera(int tempo) {
        try {
            TimeUnit.MILLISECONDS.sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("Erro.");
        }
    }

    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void loadingAnimado() {

        char[] frames = {'|', '/', '-', '\\'};

        for (int i = 0; i < 20; i++) {
            System.out.print("\rCarregando... " + frames[i % 4]);
            espera(100);
        }

        System.out.print("\rCarregando... Concluído!\n");
    }

    static void solicitarSaldo() {

        System.out.println("================================");
        System.out.println("      SALDO DO PERSONAGEM");
        System.out.println("================================");
        System.out.println();

        System.out.println(
            "Digite seu saldo no formato:"
        );

        System.out.println(
            "Platinas Ouros Pratas Cobres"
        );

        String entrada = scanner.nextLine();

        String[] moedas = entrada.split(" ");

        int platinas = Integer.parseInt(moedas[0]);
        int ouros = Integer.parseInt(moedas[1]);
        int pratas = Integer.parseInt(moedas[2]);
        int cobres = Integer.parseInt(moedas[3]);

        saldo =
                platinas * 100000L
              + ouros * 10000L
              + pratas * 100L
              + cobres;
    }

    static void mostrarSaldo() {

        long resto = saldo;

        long platinas = resto / 100000;
        resto %= 100000;

        long ouros = resto / 10000;
        resto %= 10000;

        long pratas = resto / 100;
        resto %= 100;

        long cobres = resto;

        System.out.println();
        System.out.println("Saldo:");
        System.out.println(
            platinas + "P "
          + ouros + "G "
          + pratas + "S "
          + cobres + "C"
        );
    }

    static void abrirLoja() {

        System.out.println("Iniciando Lunaris Shop...");
        espera(1000);

        System.out.println("Carregando itens...");
        espera(1000);

        System.out.println("Verificando estoque...");
        espera(1000);

        System.out.println("Conectando ao banco de dados...");
        espera(1000);

        loadingAnimado();

        espera(1000);

        limparTela();
    }

    static void menuPrincipal() {

        while (true) {

            System.out.println("================================");
            System.out.println("         LUNARIS SHOP");
            System.out.println("================================");

            mostrarSaldo();

            System.out.println();
            System.out.println("[1] Armamentos");
            System.out.println("[2] Ver Carrinho");
            System.out.println("[0] Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    menuArmamentos();
                    break;

                case 2:
                    mostrarCarrinho();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    static void menuArmamentos() {

        limparTela();

        System.out.println("================================");
        System.out.println("          ARMAMENTOS");
        System.out.println("================================");

        System.out.println("[1] Simples");
        System.out.println("[0] Voltar");

        int opcao = scanner.nextInt();

        switch (opcao) {

            case 1:
                armamentosSimples();
                break;
        }
    }

    static void armamentosSimples() {

        limparTela();

        while (true) {

            System.out.println("================================");
            System.out.println("      ARMAMENTOS SIMPLES");
            System.out.println("================================");

            System.out.println();
            System.out.println("[001] Adaga");
            System.out.println("      Dano: 1d8");
            System.out.println("      Propriedades: Mão principal, Leve, Arremessável 12m");
            System.out.println("      Tipo de dano: Perfurante");
            System.out.println("      Crítico: 18");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 1d8");
            System.out.println("      Peso: 2");
            System.out.println("      Preço: 80 Pratas");
            System.out.println();
            espera(100);

            System.out.println("[002] Par de Adagas");
            System.out.println("      Propriedades: Duas mãos, Leve, 2x Arremessável 12m");
            System.out.println("      Tipo de dano: Perfurante");
            System.out.println("      Crítico: 18");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 2d8");
            System.out.println("      Peso: 3");
            System.out.println("      Preço: 1 Ouro e 60 Pratas");
            System.out.println();
            espera(100);
    
            System.out.println("[003] Garras");
            System.out.println("      Propriedades: Versátil, Leve");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 17");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 1d8/2d8");
            System.out.println("      Peso: 2/3");
            System.out.println("      Preço: 1 Ouro e 45 Pratas");
            System.out.println();
            espera(100);
            
            System.out.println("[004] Dessangrador");
            System.out.println("      Propriedades: Mão principal, Leve");
            System.out.println("      Tipo de dano: Perfurante");
            System.out.println("      Crítico: 18");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 1d10");
            System.out.println("      Peso: 2");
            System.out.println("      Preço: 1 Ouro e 20 Pratas");
            System.out.println();
            espera(100);
            
            System.out.println("[005] Machado de Guerra");
            System.out.println("      Propriedades: Versátil, Pesado, Arremessável 6m");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 19");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 1d8/2d8");
            System.out.println("      Peso: 3");
            System.out.println("      Preço: 2 Ouros e 50 Pratas");
            System.out.println();
            espera(100);
            
            System.out.println("[006] Machadão");
            System.out.println("      Propriedades: Duas mãos, Pesado");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 20");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 2d10");
            System.out.println("      Peso: 4");
            System.out.println("      Preço: 2 Ouros");
            System.out.println();
            espera(100);
            
            System.out.println("[007] Alabarda");
            System.out.println("      Propriedades: Duas mãos, Pesado, Estendido");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 20");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 2d10");
            System.out.println("      Peso: 5");
            System.out.println("      Preço: 1 Ouro e 75 Pratas");
            System.out.println();
            espera(100);
            
            System.out.println("[008] Chama-corpos");
            System.out.println("      Propriedades: Duas mãos, Pesado");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 20");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 2d8");
            System.out.println("      Peso: 4");
            System.out.println("      Preço: 3 Ouros");
            System.out.println();
            espera(100);

            System.out.println("[009] Espada Larga");
            System.out.println("      Propriedades: Mão Principal, Leve, Arremessável 9m");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 20");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 1d6");
            System.out.println("      Peso: 2");
            System.out.println("      Preço: 50 Pratas");
            System.out.println();
            espera(100);

            System.out.println("[010] Montante");
            System.out.println("      Propriedades: Duas mãos, Pesado");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 20");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 2d12");
            System.out.println("      Peso: 4");
            System.out.println("      Preço: 2 Ouros e 25 Pratas");
            System.out.println();
            espera(100);

            System.out.println("[011] Espadas Duplas");
            System.out.println("      Propriedades: Duas mãos, Leve");
            System.out.println("      Tipo de dano: Cortante");
            System.out.println("      Crítico: 19");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 2d8");
            System.out.println("      Peso: 3");
            System.out.println("      Preço: 1 Ouro e 60 Pratas");
            System.out.println();
            espera(100);

            System.out.println("[012] Lâmina Aclarada");
            System.out.println("      Propriedades: Versátil, Leve");
            System.out.println("      Tipo de dano: Perfurante");
            System.out.println("      Crítico: 20");
            System.out.println("      Passivo: ");
            System.out.println("      Dano: 1d10/2d10");
            System.out.println("      Peso: 2");
            System.out.println("      Preço: 1 Ouro e 40 Pratas");
            System.out.println();
            espera(100);

            System.out.println("[0] Voltar");
            System.out.println();

            System.out.print("Digite o ID: ");

            int id = scanner.nextInt();

            switch (id) {

                case 1:
                    comprarItem(
                        "Adaga",
                        8000
                    );
                    break;

                case 2:
                    comprarItem(
                        "Par de Adagas",
                        16000
                    );
                    break;
                
                case 3:
                    comprarItem(
                        "Garras",
                        14500
                    );
                    break;
                
                case 4:
                    comprarItem(
                        "Dessangrador",
                        12000
                    );
                    break;
                
                case 5:
                    comprarItem(
                        "Machado de Guerra",
                        25000
                    );
                    break;
                
                case 6:
                    comprarItem(
                        "Machadão",
                        20000  
                    );
                    break;
                
                case 7:
                    comprarItem(
                        "Alabarda",
                        17500
                    );
                    break;
                
                case 8:
                    comprarItem(
                        "Chama-corpos",
                        30000
                    );
                    break;

                case 9:
                    comprarItem(
                        "Espada Larga",
                        5000
                    );
                    break;

                case 10:
                    comprarItem(
                        "Montante",
                        22500
                    );
                    break;

                case 11:
                    comprarItem(
                        "Espadas Duplas",
                        16000
                    );
                    break;

                case 12:
                    comprarItem(
                        "Lâmina Aclarada",
                        14000
                    );
                    break;
                
                case 0:
                    return;

                default:
                    System.out.println(
                        "ID inválido."
                    );
            }
        }
    }

    static void comprarItem(
            String nome,
            long preco
    ) {

        if (saldo >= preco) {

            saldo -= preco;

            carrinho[qtdCarrinho] = nome;
            qtdCarrinho++;

            System.out.println();
            System.out.println(
                nome + " adicionado ao carrinho!"
            );

        } else {

            System.out.println();
            System.out.println(
                "Saldo insuficiente!"
            );
        }

        espera(1500);
        limparTela();
    }

    static void mostrarCarrinho() {

        limparTela();

        System.out.println("================================");
        System.out.println("           CARRINHO");
        System.out.println("================================");

        if (qtdCarrinho == 0) {

            System.out.println(
                "Carrinho vazio."
            );

        } else {

            for (int i = 0; i < qtdCarrinho; i++) {

                System.out.println(
                    "- " + carrinho[i]
                );
            }
        }

        System.out.println();
        System.out.println(
            "Pressione ENTER para voltar."
        );

        scanner.nextLine();
        scanner.nextLine();

        limparTela();
    }

    public static void main(String[] args) {

        solicitarSaldo();

        abrirLoja();

        menuPrincipal();
    }
}
