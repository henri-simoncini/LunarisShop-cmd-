import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static long saldo;

    static String[] carrinho = new String[200];
    static int qtdCarrinho = 0;

    // ============================================================
    // UTILITÁRIOS
    // ============================================================

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

    static void pausar() {
        System.out.println();
        System.out.println("Pressione ENTER para continuar.");
        scanner.nextLine();
        limparTela();
    }

    // ============================================================
    // SALDO
    // ============================================================

    static void solicitarSaldo() {
        System.out.println("================================");
        System.out.println("      SALDO DO PERSONAGEM");
        System.out.println("================================");
        System.out.println();
        System.out.println("Digite seu saldo no formato:");
        System.out.println("Platinas Ouros Pratas Cobres");

        String entrada = scanner.nextLine();
        String[] moedas = entrada.split(" ");

        int platinas = Integer.parseInt(moedas[0]);
        int ouros    = Integer.parseInt(moedas[1]);
        int pratas   = Integer.parseInt(moedas[2]);
        int cobres   = Integer.parseInt(moedas[3]);

        saldo = platinas * 100000L
              + ouros    * 10000L
              + pratas   * 100L
              + cobres;
    }

    static void mostrarSaldo() {
        long resto   = saldo;
        long platinas = resto / 100000; resto %= 100000;
        long ouros    = resto / 10000;  resto %= 10000;
        long pratas   = resto / 100;    resto %= 100;
        long cobres   = resto;

        System.out.println();
        System.out.println("Saldo:");
        System.out.println(platinas + "P  " + ouros + "G  " + pratas + "S  " + cobres + "C");
    }

    static String formatarPreco(long preco) {
        long resto    = preco;
        long platinas = resto / 100000; resto %= 100000;
        long ouros    = resto / 10000;  resto %= 10000;
        long pratas   = resto / 100;    resto %= 100;
        long cobres   = resto;

        StringBuilder sb = new StringBuilder();
        if (platinas > 0) sb.append(platinas).append("P ");
        if (ouros    > 0) sb.append(ouros).append("G ");
        if (pratas   > 0) sb.append(pratas).append("S ");
        if (cobres   > 0) sb.append(cobres).append("C");
        if (sb.length() == 0) sb.append("Grátis");
        return sb.toString().trim();
    }

    // ============================================================
    // ABERTURA
    // ============================================================

    static void abrirLoja() {
        System.out.println("Iniciando Lunaris Shop...");   espera(800);
        System.out.println("Carregando itens...");          espera(800);
        System.out.println("Verificando estoque...");       espera(800);
        System.out.println("Conectando ao banco de dados..."); espera(800);
        loadingAnimado();
        espera(800);
        limparTela();
    }

    // ============================================================
    // COMPRA
    // ============================================================

    static void comprarItem(String nome, long preco) {
        if (saldo >= preco) {
            saldo -= preco;
            if (qtdCarrinho < carrinho.length) {
                carrinho[qtdCarrinho++] = nome + " (" + formatarPreco(preco) + ")";
            }
            System.out.println();
            System.out.println("✔ " + nome + " adicionado ao carrinho!");
        } else {
            System.out.println();
            System.out.println("✘ Saldo insuficiente! Preço: " + formatarPreco(preco));
        }
        espera(1500);
        limparTela();
    }

    // ============================================================
    // CARRINHO
    // ============================================================

    static void mostrarCarrinho() {
        limparTela();
        System.out.println("================================");
        System.out.println("           CARRINHO");
        System.out.println("================================");
        
        if (qtdCarrinho == 0) {
            System.out.println("Carrinho vazio.");
        } else {
            for (int i = 0; i < qtdCarrinho; i++) {
                System.out.println((i + 1) + ". " + carrinho[i]);
            }
        }
        pausar();
        System.out.println("[1] Remover Item");
System.out.println("[0] Voltar");

int opcao = scanner.nextInt();

switch(opcao) {

    case 1:
        removerItemCarrinho();
        break;

    case 0:
        return;
}
    }
    static void removerItemCarrinho() {

    if (qtdCarrinho == 0) {
        System.out.println("Carrinho vazio.");
        return;
    }

    System.out.println("Itens do carrinho:");

    for (int i = 0; i < qtdCarrinho; i++) {

        System.out.println(
            "[" + (i + 1) + "] "
            + carrinho[i]
        );
    }

    System.out.print("Qual item deseja remover? ");

    int escolha = scanner.nextInt() - 1;

    if (escolha < 0 || escolha >= qtdCarrinho) {

        System.out.println("Item inválido.");
        return;
    }

    saldo += precoCarrinho[escolha];

    System.out.println(
        carrinho[escolha]
        + " removido do carrinho."
    );

    for (int i = escolha; i < qtdCarrinho - 1; i++) {

        carrinho[i] = carrinho[i + 1];
        precoCarrinho[i] = precoCarrinho[i + 1];
    }

    qtdCarrinho--;
    }
    // ============================================================
    // MENU PRINCIPAL
    // ============================================================

    static void menuPrincipal() {
        while (true) {
            System.out.println("================================");
            System.out.println("         LUNARIS SHOP");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            System.out.println("[1] Armamentos");
            System.out.println("[2] Vestimentas");
            System.out.println("[3] Acessórios");
            System.out.println("[4] Itens");
            System.out.println("[5] Montarias");
            System.out.println("[6] Ver Carrinho");
            System.out.println("[0] Sair");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: menuArmamentos();   break;
                case 2: menuVestimentas();  break;
                case 3: listarAcessorios(); break;
                case 4: listarItens();      break;
                case 5: listarMontarias();  break;
                case 6: mostrarCarrinho();  break;
                case 0: System.exit(0);     break;
                default: System.out.println("Opção inválida."); espera(800); limparTela();
            }
        }
    }

    // ============================================================
    // MENU ARMAMENTOS
    // ============================================================

    static void menuArmamentos() {
        while (true) {
            limparTela();
            System.out.println("================================");
            System.out.println("          ARMAMENTOS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            System.out.println("[1] Simples");
            System.out.println("[2] Complexos");
            System.out.println("[3] Exóticos");
            System.out.println("[4] Off-hands");
            System.out.println("[5] Munições e Arremessáveis");
            System.out.println("[6] Especiais");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: menuTipoArma("SIMPLES",   "simples");   break;
                case 2: menuTipoArma("COMPLEXOS", "complexos"); break;
                case 3: menuTipoArma("EXÓTICOS",  "exoticos");  break;
                case 4: listarOffHands();                        break;
                case 5: listarMunicoes();                        break;
                case 6: listarEspeciais();                       break;
                case 0: limparTela(); return;
                default: System.out.println("Opção inválida."); espera(800);
            }
        }
    }

    static void menuTipoArma(String titulo, String tipo) {
        while (true) {
            limparTela();
            System.out.println("================================");
            System.out.println("      ARMAMENTOS " + titulo);
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            System.out.println("[1] Corpo-a-corpo");
            System.out.println("[2] Longa Distância");
            System.out.println("[3] Mágico");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (tipo.equals("simples"))   armamentosSimplesCaC();
                    else if (tipo.equals("complexos")) armamentosComplexosCaC();
                    else armamentosExoticosCaC();
                    break;
                case 2:
                    if (tipo.equals("simples"))   armamentosSimplesDist();
                    else if (tipo.equals("complexos")) armamentosComplexosDist();
                    else armamentosExoticosDist();
                    break;
                case 3:
                    if (tipo.equals("simples"))   armamentosSimplesMago();
                    else if (tipo.equals("complexos")) armamentosComplexosMago();
                    else armamentosExoticosMago();
                    break;
                case 0: return;
                default: System.out.println("Opção inválida."); espera(800);
            }
        }
    }

    // ============================================================
    // ARMAMENTOS SIMPLES — CORPO-A-CORPO
    // ============================================================

    static void armamentosSimplesCaC() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("   ARMAMENTOS SIMPLES — CaC");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirArmaSimples(1,  "Adaga",          "[M][L][A 12m]", "Perfurante", 18, "1d8",      2,  8000);
            exibirArmaSimples(2,  "Par de Adagas",  "[D][L][A 12m]", "Perfurante", 18, "2d8",      3,  16000);
            exibirArmaSimples(3,  "Garras",          "[V][L]",        "Cortante",   17, "1d8/2d8",  2,  14500);
            exibirArmaSimples(4,  "Dessangrador",    "[M][L]",        "Perfurante", 18, "1d10",     2,  12000);
            exibirArmaSimples(5,  "Machado de Guerra","[V][P][A 6m]","Cortante",   19, "1d8/2d8",  3,  25000);
            exibirArmaSimples(6,  "Machadão",        "[D][P]",        "Cortante",   20, "2d10",     4,  20000);
            exibirArmaSimples(7,  "Alabarda",        "[D][P][E]",     "Cortante",   20, "2d10",     5,  17500);
            exibirArmaSimples(8,  "Chama-corpos",    "[D][P]",        "Cortante",   20, "2d8",      4,  30000);
            exibirArmaSimples(9,  "Espada Larga",    "[M][L][A 9m]", "Cortante",   20, "1d6",      2,  5000);
            exibirArmaSimples(10, "Montante",        "[D][P]",        "Cortante",   20, "2d12",     4,  22500);
            exibirArmaSimples(11, "Espadas Duplas",  "[D][L]",        "Cortante",   19, "2d8",      3,  16000);
            exibirArmaSimples(12, "Lâmina Aclarada", "[V][L]",        "Perfurante", 20, "1d10/2d10",2, 14000);
            exibirArmaSimples(13, "Lança",           "[V][L][E][B][A 9m]","Perfurante",20,"1d6/2d6",3, 8500);
            exibirArmaSimples(14, "Pique",           "[D][L][E]",     "Perfurante", 20, "2d6",      4,  12000);
            exibirArmaSimples(15, "Archa",           "[D][L][E]",     "Perfurante", 20, "2d8",      4,  16000);
            exibirArmaSimples(16, "Lança Garceira",  "[V][L][E][A 15m]","Perfurante",20,"1d8/2d8", 3,  17000);
            exibirArmaSimples(17, "Martelo",         "[V][P]",        "Impactante", 20, "1d6/2d6",  3,  10000);
            exibirArmaSimples(18, "Martelo de Batalha","[D][P]",      "Impactante", 20, "2d8",      4,  16500);
            exibirArmaSimples(19, "Martelo Elevado", "[D][P]",        "Impactante", 20, "2d6",      4,  17500);
            exibirArmaSimples(20, "Martelo Fúnebre", "[D][P]",        "Impactante", 20, "2d6",      4,  20000);
            exibirArmaSimples(21, "Maça",            "[V][P][A 6m]", "Impactante", 20, "1d4/2d4",  3,  7000);
            exibirArmaSimples(22, "Maça Pesada",     "[D][P]",        "Impactante", 20, "2d4",      4,  10000);
            exibirArmaSimples(23, "Mangual",         "[D][P][E]",     "Perfurante", 20, "2d6",      5,  22500);
            exibirArmaSimples(24, "Maça Pétrea",     "[M][P][A 6m]", "Impactante", 20, "1d4",      3,  5000);
            exibirArmaSimples(25, "Luvas de Lutador","[V][P]",        "Impactante", 20, "1d4/2d4",  3,  26000);
            exibirArmaSimples(26, "Braçadeiras de Batalha","[V][P]", "Impactante", 20, "1d6/2d6",  3,  26000);
            exibirArmaSimples(27, "Manoplas Cravadas","[V][P]",       "Impactante", 20, "1d6/2d6",  3,  26000);
            exibirArmaSimples(28, "Luvas Ursinas",   "[V][P]",        "Impactante", 20, "1d6/2d6",  3,  26000);
            exibirArmaSimples(29, "Bordão",          "[V][L]",        "Impactante", 20, "1d8/2d8",  2,  22500);
            exibirArmaSimples(30, "Cajado Férreo",   "[V][L]",        "Impactante", 20, "1d8/2d8",  2,  16000);
            exibirArmaSimples(31, "Cajado Bilaminado","[V][L]",       "Cortante",   20, "1d8/2d8",  2,  20000);
            exibirArmaSimples(32, "Cajado de Monge Negro","[V][L]",   "Cortante",   20, "1d8/2d8",  2,  20000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case  1: comprarItem("Adaga",              8000);  break;
                case  2: comprarItem("Par de Adagas",     16000);  break;
                case  3: comprarItem("Garras",            14500);  break;
                case  4: comprarItem("Dessangrador",      12000);  break;
                case  5: comprarItem("Machado de Guerra", 25000);  break;
                case  6: comprarItem("Machadão",          20000);  break;
                case  7: comprarItem("Alabarda",          17500);  break;
                case  8: comprarItem("Chama-corpos",      30000);  break;
                case  9: comprarItem("Espada Larga",       5000);  break;
                case 10: comprarItem("Montante",          22500);  break;
                case 11: comprarItem("Espadas Duplas",    16000);  break;
                case 12: comprarItem("Lâmina Aclarada",   14000);  break;
                case 13: comprarItem("Lança",              8500);  break;
                case 14: comprarItem("Pique",             12000);  break;
                case 15: comprarItem("Archa",             16000);  break;
                case 16: comprarItem("Lança Garceira",    17000);  break;
                case 17: comprarItem("Martelo",           10000);  break;
                case 18: comprarItem("Martelo de Batalha",16500);  break;
                case 19: comprarItem("Martelo Elevado",   17500);  break;
                case 20: comprarItem("Martelo Fúnebre",   20000);  break;
                case 21: comprarItem("Maça",               7000);  break;
                case 22: comprarItem("Maça Pesada",       10000);  break;
                case 23: comprarItem("Mangual",           22500);  break;
                case 24: comprarItem("Maça Pétrea",        5000);  break;
                case 25: comprarItem("Luvas de Lutador",  26000);  break;
                case 26: comprarItem("Braçadeiras de Batalha",26000); break;
                case 27: comprarItem("Manoplas Cravadas", 26000);  break;
                case 28: comprarItem("Luvas Ursinas",     26000);  break;
                case 29: comprarItem("Bordão",            22500);  break;
                case 30: comprarItem("Cajado Férreo",     16000);  break;
                case 31: comprarItem("Cajado Bilaminado", 20000);  break;
                case 32: comprarItem("Cajado de Monge Negro",20000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirArmaSimples(int id, String nome, String prop, String tipoDano, int critico, String dano, int peso, long preco) {
        System.out.printf("[%03d] %-28s  Prop: %-20s  Dano: %-12s  Crit: %d  Tipo: %-12s  Peso: %d  Preço: %s%n",
            id, nome, prop, dano, critico, tipoDano, peso, formatarPreco(preco));
    }

    // ============================================================
    // ARMAMENTOS SIMPLES — LONGA DISTÂNCIA
    // ============================================================

    static void armamentosSimplesDist() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println(" ARMAMENTOS SIMPLES — DIST.");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirArmaDist(33, "Arco",               "[D][L]", "25m","Perfurante", 19,"2d8",  3, 17500);
            exibirArmaDist(34, "Arco de Guerra",      "[D][P]", "28m","Perfurante", 20,"2d8",  4, 20000);
            exibirArmaDist(35, "Arco Longo",          "[D][L]", "28m","Perfurante", 19,"2d6",  3, 17500);
            exibirArmaDist(36, "Arco Sussurrante",    "[D][L]", "25m","Perfurante", 19,"2d6",  3, 22000);
            exibirArmaDist(37, "Besta",               "[D][L]", "28m","Perfurante", 18,"2d10", 3, 25000);
            exibirArmaDist(38, "Besta Pesada",        "[D][P]", "32m","Perfurante", 20,"2d12", 4, 30000);
            exibirArmaDist(39, "Besta Leve",          "[M][L]", "25m","Perfurante", 19,"1d10", 2, 15000);
            exibirArmaDist(40, "Repetidor Lamentoso", "[D][L]", "28m","Perfurante", 18,"2d8",  3, 27500);
            exibirArmaDist(41, "Cajado de Predador",  "[D][L]", "15m","Cortante",   18,"2d6",  3, 40000);
            exibirArmaDist(42, "Cajado da Lua de Sangue","[D][P]","15m","Cortante",  19,"2d8",  4, 42500);
            exibirArmaDist(43, "Cajado Primitivo",    "[D][P]", "15m","Impactante", 20,"2d4",  4, 50000);
            exibirArmaDist(44, "Cajado Enraizado",    "[D][L]", "15m","Perfurante", 20,"2d4",  3, 50000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 33: comprarItem("Arco",                  17500); break;
                case 34: comprarItem("Arco de Guerra",        20000); break;
                case 35: comprarItem("Arco Longo",            17500); break;
                case 36: comprarItem("Arco Sussurrante",      22000); break;
                case 37: comprarItem("Besta",                 25000); break;
                case 38: comprarItem("Besta Pesada",          30000); break;
                case 39: comprarItem("Besta Leve",            15000); break;
                case 40: comprarItem("Repetidor Lamentoso",   27500); break;
                case 41: comprarItem("Cajado de Predador",    40000); break;
                case 42: comprarItem("Cajado da Lua de Sangue",42500); break;
                case 43: comprarItem("Cajado Primitivo",      50000); break;
                case 44: comprarItem("Cajado Enraizado",      50000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirArmaDist(int id, String nome, String prop, String alcance, String tipoDano, int critico, String dano, int peso, long preco) {
        System.out.printf("[%03d] %-30s  Prop: %-12s  Alc: %-5s  Dano: %-12s  Crit: %d  Tipo: %-12s  Peso: %d  Preço: %s%n",
            id, nome, prop, alcance, dano, critico, tipoDano, peso, formatarPreco(preco));
    }

    // ============================================================
    // ARMAMENTOS SIMPLES — MÁGICO
    // ============================================================

    static void armamentosSimplesMago() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("  ARMAMENTOS SIMPLES — MÁGICO");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirArmaMago(45, "Cajado de Fogo",            "[M][L]","21m","Queimante",  20,"1d8",  2, 30000);
            exibirArmaMago(46, "Cajado de Fogo Elevado",    "[D][P]","12m","Queimante",  20,"2d12", 4, 56000);
            exibirArmaMago(47, "Cajado Infernal",           "[D][L]","18m","Queimante",  20,"2d10", 3, 40000);
            exibirArmaMago(48, "Cajado Incendiário",        "[M][L]","21m","Queimante",  19,"1d8",  2, 32500);
            exibirArmaMago(49, "Cajado Arcano",             "[M][L]","21m","Arcano",     20,"1d6",  2, 30000);
            exibirArmaMago(50, "Cajado Arcano Elevado",     "[D][P]","25m","Arcano",     20,"2d10", 4, 51500);
            exibirArmaMago(51, "Cajado Enigmático",         "[D][L]","21m","Arcano",     20,"2d8",  3, 36500);
            exibirArmaMago(52, "Cajado Feiticeiro",         "[M][L]","21m","Arcano",     19,"1d6",  2, 32500);
            exibirArmaMago(53, "Cajado de Gelo",            "[M][L]","18m","Congelante", 20,"1d4",  2, 25000);
            exibirArmaMago(54, "Cajado de Gelo Elevado",    "[D][P]","21m","Congelante", 20,"2d8",  4, 48000);
            exibirArmaMago(55, "Cajado Glacial",            "[D][L]","18m","Congelante", 20,"2d6",  3, 40000);
            exibirArmaMago(56, "Cajado Enregelante",        "[M][L]","15m","Congelante", 19,"1d6",  2, 38500);
            exibirArmaMago(57, "Cajado Amaldiçoado",        "[M][L]","21m","Necrótico",  20,"1d10", 2, 40000);
            exibirArmaMago(58, "Cajado Amaldiçoado Elevado","[D][P]","15m","Necrótico",  20,"2d12", 4, 62500);
            exibirArmaMago(59, "Cajado Demoníaco",          "[D][L]","18m","Necrótico",  20,"2d12", 3, 64000);
            exibirArmaMago(60, "Cajado Execrado",           "[M][L]","18m","Necrótico",  19,"1d10", 2, 32500);
            exibirArmaMago(61, "Cajado da Natureza",        "[M][L]","21m","Venenoso",   20,"1d4",  2, 40000);
            exibirArmaMago(62, "Cajado da Natureza Elevado","[D][P]","21m","Venenoso",   20,"2d6",  4, 70000);
            exibirArmaMago(63, "Cajado Selvagem",           "[D][L]","21m","Venenoso",   20,"2d6",  3, 57500);
            exibirArmaMago(64, "Cajado Druídico",           "[M][L]","21m","Venenoso",   20,"1d4",  2, 42500);
            exibirArmaMago(65, "Cajado Sagrado",            "[M][L]","15m","Radiante",   20,"1d4",  2, 37500);
            exibirArmaMago(66, "Cajado Sagrado Elevado",    "[D][P]","15m","Radiante",   20,"2d6",  4, 70000);
            exibirArmaMago(67, "Cajado Divino",             "[D][L]","15m","Radiante",   20,"2d6",  3, 50000);
            exibirArmaMago(68, "Cajado Avivador",           "[M][L]","15m","Radiante",   20,"1d6",  2, 50000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 45: comprarItem("Cajado de Fogo",             30000); break;
                case 46: comprarItem("Cajado de Fogo Elevado",     56000); break;
                case 47: comprarItem("Cajado Infernal",            40000); break;
                case 48: comprarItem("Cajado Incendiário",         32500); break;
                case 49: comprarItem("Cajado Arcano",              30000); break;
                case 50: comprarItem("Cajado Arcano Elevado",      51500); break;
                case 51: comprarItem("Cajado Enigmático",          36500); break;
                case 52: comprarItem("Cajado Feiticeiro",          32500); break;
                case 53: comprarItem("Cajado de Gelo",             25000); break;
                case 54: comprarItem("Cajado de Gelo Elevado",     48000); break;
                case 55: comprarItem("Cajado Glacial",             40000); break;
                case 56: comprarItem("Cajado Enregelante",         38500); break;
                case 57: comprarItem("Cajado Amaldiçoado",         40000); break;
                case 58: comprarItem("Cajado Amaldiçoado Elevado", 62500); break;
                case 59: comprarItem("Cajado Demoníaco",           64000); break;
                case 60: comprarItem("Cajado Execrado",            32500); break;
                case 61: comprarItem("Cajado da Natureza",         40000); break;
                case 62: comprarItem("Cajado da Natureza Elevado", 70000); break;
                case 63: comprarItem("Cajado Selvagem",            57500); break;
                case 64: comprarItem("Cajado Druídico",            42500); break;
                case 65: comprarItem("Cajado Sagrado",             37500); break;
                case 66: comprarItem("Cajado Sagrado Elevado",     70000); break;
                case 67: comprarItem("Cajado Divino",              50000); break;
                case 68: comprarItem("Cajado Avivador",            50000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirArmaMago(int id, String nome, String prop, String alcance, String tipoDano, int critico, String dano, int peso, long preco) {
        System.out.printf("[%03d] %-32s  Prop: %-8s  Alc: %-5s  Dano: %-8s  Crit: %d  Tipo: %-12s  Peso: %d  Preço: %s%n",
            id, nome, prop, alcance, dano, critico, tipoDano, peso, formatarPreco(preco));
    }

    // ============================================================
    // ARMAMENTOS COMPLEXOS — CORPO-A-CORPO
    // ============================================================

    static void armamentosComplexosCaC() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("  ARMAMENTOS COMPLEXOS — CaC");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirArmaSimples(69,  "Presa Demoníaca",    "[M][L]","Cortante",  18,"1d8",       1, 45000);
            exibirArmaSimples(70,  "Mortíficos",          "[V][L]","Cortante",  17,"1d10/2d10", 1, 52500);
            exibirArmaSimples(71,  "Fúria Contida",       "[V][L]","Perfurante",19,"1d8/2d8",   1, 52500);
            exibirArmaSimples(72,  "Gêmeas Aniquiladoras","[V][L]","Perfurante",18,"1d10/2d10", 1, 60000);
            exibirArmaSimples(73,  "Segadeira Infernal",  "[D][L]","Cortante",  19,"2d10",      1, 56000);
            exibirArmaSimples(74,  "Patas de Urso",       "[V][L]","Cortante",  19,"1d10/2d10", 1, 62500);
            exibirArmaSimples(75,  "Quebra-reino",        "[D][P]","Cortante",  20,"2d12",      1, 60000);
            exibirArmaSimples(76,  "Foice de Cristal",    "[D][L]","Cortante",  20,"2d10",      1, 57500);
            exibirArmaSimples(77,  "Espada Entalhada",    "[D][P]","Perfurante",20,"2d12",      1, 65000);
            exibirArmaSimples(78,  "Par de Galatinas",    "[V][L]","Cortante",  19,"1d8/2d8",   1, 70000);
            exibirArmaSimples(79,  "Cria-reis",           "[D][P]","Cortante",  20,"2d10",      1, 72500);
            exibirArmaSimples(80,  "Lâmina da Infinidade","[M][P]","Cortante",  19,"1d12",      1, 68500);
            exibirArmaSimples(81,  "Caça-espíritos",      "[D][L]","Perfurante",19,"1d8/2d8",   1, 62500);
            exibirArmaSimples(82,  "Lança Trina",         "[D][L]","Perfurante",19,"2d8",       1, 66000);
            exibirArmaSimples(83,  "Alvorada",            "[M][P]","Perfurante",19,"2d10",      1, 80000);
            exibirArmaSimples(84,  "Archa Fraturada",     "[D][P]","Perfurante",19,"1d10/2d10", 1, 74500);
            exibirArmaSimples(85,  "Martelos de Forja",   "[D][P]","Impactante",20,"1d8/2d8",   1, 80000);
            exibirArmaSimples(86,  "Guarda-bosques",      "[D][P]","Impactante",20,"2d10",      1, 92500);
            exibirArmaSimples(87,  "Mão da Justiça",      "[D][P]","Impactante",20,"2d8",       1, 96000);
            exibirArmaSimples(88,  "Maça de Íncubo",      "[M][P]","Impactante",20,"1d6",       1, 82500);
            exibirArmaSimples(89,  "Maça Cambriana",      "[D][P]","Perfurante",20,"2d6",       1, 90000);
            exibirArmaSimples(90,  "Jurador",             "[D][P]","Impactante",20,"2d8",       1,100000);
            exibirArmaSimples(91,  "Monarca Tempestuoso", "[M][P]","Impactante",20,"1d6",       1,102500);
            exibirArmaSimples(92,  "Mãos Infernais",      "[V][P]","Impactante",20,"1d8/2d8",   1, 90000);
            exibirArmaSimples(93,  "Cestus Golpeadores",  "[V][P]","Impactante",19,"1d8/2d8",   1, 96500);
            exibirArmaSimples(94,  "Punhos de Avalon",    "[V][P]","Impactante",19,"1d10/2d10", 1,100000);
            exibirArmaSimples(95,  "Braçadeiras Pulsantes","[V][P]","Impactante",20,"1d8/2d8",  1,105000);
            exibirArmaSimples(96,  "Seganímica",          "[V][L]","Cortante",  19,"1d8/2d8",   1,102500);
            exibirArmaSimples(97,  "Cajado do Equilíbrio","[V][L]","Impactante",20,"1d8/2d8",   1,107500);
            exibirArmaSimples(98,  "Buscador do Graal",   "[V][L]","Impactante",20,"1d8/2d8",   1,112500);
            exibirArmaSimples(99,  "Lâminas Gêmeas Fantasmagóricas","[V][L]","Cortante",19,"1d10/2d10",1,120000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 69:  comprarItem("Presa Demoníaca",              45000); break;
                case 70:  comprarItem("Mortíficos",                   52500); break;
                case 71:  comprarItem("Fúria Contida",                52500); break;
                case 72:  comprarItem("Gêmeas Aniquiladoras",         60000); break;
                case 73:  comprarItem("Segadeira Infernal",           56000); break;
                case 74:  comprarItem("Patas de Urso",                62500); break;
                case 75:  comprarItem("Quebra-reino",                 60000); break;
                case 76:  comprarItem("Foice de Cristal",             57500); break;
                case 77:  comprarItem("Espada Entalhada",             65000); break;
                case 78:  comprarItem("Par de Galatinas",             70000); break;
                case 79:  comprarItem("Cria-reis",                    72500); break;
                case 80:  comprarItem("Lâmina da Infinidade",         68500); break;
                case 81:  comprarItem("Caça-espíritos",               62500); break;
                case 82:  comprarItem("Lança Trina",                  66000); break;
                case 83:  comprarItem("Alvorada",                     80000); break;
                case 84:  comprarItem("Archa Fraturada",              74500); break;
                case 85:  comprarItem("Martelos de Forja",            80000); break;
                case 86:  comprarItem("Guarda-bosques",               92500); break;
                case 87:  comprarItem("Mão da Justiça",               96000); break;
                case 88:  comprarItem("Maça de Íncubo",               82500); break;
                case 89:  comprarItem("Maça Cambriana",               90000); break;
                case 90:  comprarItem("Jurador",                     100000); break;
                case 91:  comprarItem("Monarca Tempestuoso",         102500); break;
                case 92:  comprarItem("Mãos Infernais",               90000); break;
                case 93:  comprarItem("Cestus Golpeadores",           96500); break;
                case 94:  comprarItem("Punhos de Avalon",            100000); break;
                case 95:  comprarItem("Braçadeiras Pulsantes",       105000); break;
                case 96:  comprarItem("Seganímica",                  102500); break;
                case 97:  comprarItem("Cajado do Equilíbrio",        107500); break;
                case 98:  comprarItem("Buscador do Graal",           112500); break;
                case 99:  comprarItem("Lâminas Gêmeas Fantasmagóricas",120000); break;
                case  0:  limparTela(); return;
                default:  System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    // ============================================================
    // ARMAMENTOS COMPLEXOS — LONGA DISTÂNCIA
    // ============================================================

    static void armamentosComplexosDist() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println(" ARMAMENTOS COMPLEXOS — DIST.");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirArmaSimples(100,"Arco Plangente",           "[D][P]","Perfurante",19,"2d8",  1, 80000);
            exibirArmaSimples(101,"Arco Badônico",            "[D][P]","Perfurante",19,"2d10", 1, 92500);
            exibirArmaSimples(102,"Fura-bruma",               "[D][P]","Perfurante",19,"2d10", 1,100000);
            exibirArmaSimples(103,"Arco do Andarilho Celeste","[D][P]","Perfurante",19,"2d10", 1,114500);
            exibirArmaSimples(104,"Lança-virotes",            "[D][L]","Perfurante",19,"2d8",  1,122500);
            exibirArmaSimples(105,"Arco de Cerco",            "[D][P]","Perfurante",19,"2d10", 1,126500);
            exibirArmaSimples(106,"Modelador de Energia",     "[D][P]","Radiante",  19,"2d6",  1,119000);
            exibirArmaSimples(107,"Detonadores Reluzentes",   "[D][L]","Perfurante",19,"2d8",  1,131000);
            exibirArmaSimples(108,"Cajado Endemoniado",       "[D][L]","Perfurante",20,"2d8",  1,120000);
            exibirArmaSimples(109,"Cajado Invocador da Luz",  "[D][L]","Perfurante",20,"2d8",  1,120000);
            exibirArmaSimples(110,"Cajado Rúnico da Terra",   "[D][L]","Impactante",20,"2d6",  1,120000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 100: comprarItem("Arco Plangente",            80000); break;
                case 101: comprarItem("Arco Badônico",             92500); break;
                case 102: comprarItem("Fura-bruma",               100000); break;
                case 103: comprarItem("Arco do Andarilho Celeste",114500); break;
                case 104: comprarItem("Lança-virotes",            122500); break;
                case 105: comprarItem("Arco de Cerco",            126500); break;
                case 106: comprarItem("Modelador de Energia",     119000); break;
                case 107: comprarItem("Detonadores Reluzentes",   131000); break;
                case 108: comprarItem("Cajado Endemoniado",       120000); break;
                case 109: comprarItem("Cajado Invocador da Luz",  120000); break;
                case 110: comprarItem("Cajado Rúnico da Terra",   120000); break;
                case   0: limparTela(); return;
                default:  System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    // ============================================================
    // ARMAMENTOS COMPLEXOS — MÁGICO
    // ============================================================

    static void armamentosComplexosMago() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println(" ARMAMENTOS COMPLEXOS — MÁGICO");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirArmaSimples(111,"Cajado Sulfuroso",                "[D][P]","Queimante",  18,"2d10",1,122500);
            exibirArmaSimples(112,"Cajado Fulgurante",               "[D][P]","Queimante",  18,"2d12",1,135000);
            exibirArmaSimples(113,"Canção da Alvorada",              "[D][L]","Queimante",  18,"2d10",1,132000);
            exibirArmaSimples(114,"Cajado do Andarilho Flamejante",  "[M][L]","Queimante",  18,"1d10",1,132000);
            exibirArmaSimples(115,"Cajado Oculto",                   "[D][P]","Arcano",     20,"2d10",1,130000);
            exibirArmaSimples(116,"Local Malévolo",                  "[D][L]","Arcano",     20,"2d8", 1,140000);
            exibirArmaSimples(117,"Som Equilibrado",                 "[D][L]","Arcano",     20,"2d8", 1,140000);
            exibirArmaSimples(118,"Cajado Astral",                   "[D][P]","Arcano",     20,"2d10",1,150000);
            exibirArmaSimples(119,"Cajado de Sincelo",               "[D][P]","Congelante", 20,"2d8", 1,144500);
            exibirArmaSimples(120,"Prisma Geleterno",                "[D][L]","Congelante", 20,"2d10",1,150000);
            exibirArmaSimples(121,"Uivo Frio",                       "[M][L]","Congelante", 20,"1d10",1,165000);
            exibirArmaSimples(122,"Cajado Ártico",                   "[D][P]","Congelante", 20,"2d12",1,172500);
            exibirArmaSimples(123,"Caveira Amaldiçoada",             "[D][L]","Necrótico",  19,"2d10",1,157500);
            exibirArmaSimples(124,"Cajado da Danação",               "[D][P]","Necrótico",  19,"2d12",1,162500);
            exibirArmaSimples(125,"Chama-sombra",                    "[M][L]","Necrótico",  19,"1d10",1,180000);
            exibirArmaSimples(126,"Cajado Pútrido",                  "[M][P]","Necrótico",  19,"1d12",1,172500);
            exibirArmaSimples(127,"Cajado Pustulento",               "[D][L]","Venenoso",   20,"2d6", 1,170000);
            exibirArmaSimples(128,"Cajado Rampante",                 "[D][L]","Venenoso",   20,"2d6", 1,181000);
            exibirArmaSimples(129,"Raiz Férrea",                     "[M][L]","Venenoso",   20,"1d8", 1,177500);
            exibirArmaSimples(130,"Cajado de Crosta Forjada",        "[M][L]","Venenoso",   20,"1d6", 1,194500);
            exibirArmaSimples(131,"Cajado Corrompido",               "[D][P]","Radiante",   20,"2d6", 1,200000);
            exibirArmaSimples(132,"Cajado da Redenção",              "[D][P]","Radiante",   20,"2d6", 1,200000);
            exibirArmaSimples(133,"Queda Santa",                     "[M][L]","Radiante",   20,"1d8", 1,207500);
            exibirArmaSimples(134,"Cajado Exaltado",                 "[D][P]","Radiante",   20,"2d8", 1,236000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 111: comprarItem("Cajado Sulfuroso",               122500); break;
                case 112: comprarItem("Cajado Fulgurante",              135000); break;
                case 113: comprarItem("Canção da Alvorada",             132000); break;
                case 114: comprarItem("Cajado do Andarilho Flamejante", 132000); break;
                case 115: comprarItem("Cajado Oculto",                  130000); break;
                case 116: comprarItem("Local Malévolo",                 140000); break;
                case 117: comprarItem("Som Equilibrado",                140000); break;
                case 118: comprarItem("Cajado Astral",                  150000); break;
                case 119: comprarItem("Cajado de Sincelo",              144500); break;
                case 120: comprarItem("Prisma Geleterno",               150000); break;
                case 121: comprarItem("Uivo Frio",                      165000); break;
                case 122: comprarItem("Cajado Ártico",                  172500); break;
                case 123: comprarItem("Caveira Amaldiçoada",            157500); break;
                case 124: comprarItem("Cajado da Danação",              162500); break;
                case 125: comprarItem("Chama-sombra",                   180000); break;
                case 126: comprarItem("Cajado Pútrido",                 172500); break;
                case 127: comprarItem("Cajado Pustulento",              170000); break;
                case 128: comprarItem("Cajado Rampante",                181000); break;
                case 129: comprarItem("Raiz Férrea",                    177500); break;
                case 130: comprarItem("Cajado de Crosta Forjada",       194500); break;
                case 131: comprarItem("Cajado Corrompido",              200000); break;
                case 132: comprarItem("Cajado da Redenção",             200000); break;
                case 133: comprarItem("Queda Santa",                    207500); break;
                case 134: comprarItem("Cajado Exaltado",                236000); break;
                case   0: limparTela(); return;
                default:  System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    // ============================================================
    // ARMAMENTOS EXÓTICOS — placeholder (estrutura pronta)
    // ============================================================

    static void armamentosExoticosCaC() {
        limparTela();
        System.out.println("================================");
        System.out.println("  ARMAMENTOS EXÓTICOS — CaC");
        System.out.println("================================");
        System.out.println("(Em breve...)");
        pausar();
    }

    static void armamentosExoticosDist() {
        limparTela();
        System.out.println("================================");
        System.out.println(" ARMAMENTOS EXÓTICOS — DIST.");
        System.out.println("================================");
        System.out.println("(Em breve...)");
        pausar();
    }

    static void armamentosExoticosMago() {
        limparTela();
        System.out.println("================================");
        System.out.println(" ARMAMENTOS EXÓTICOS — MÁGICO");
        System.out.println("================================");
        System.out.println("(Em breve...)");
        pausar();
    }

    // ============================================================
    // OFF-HANDS
    // ============================================================

    static void listarOffHands() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("           OFF-HANDS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirOffHand(1,  "Tocha",              "[L]","Recebe +2 acerto armas leves, +1 armas pesadas.",              8000);
            exibirOffHand(2,  "Brumário",           "[L]","Reduz 1 rodada o recarga de habilidades de arma.",            25000);
            exibirOffHand(3,  "Bengala Maligna",    "[P]","Aumenta duração de efeitos negativos em 1 rodada.",           16000);
            exibirOffHand(4,  "Lume Críptico",      "[L]","Aumenta dano básico em 2 dados (pesada) ou 1 dado (leve).",   30000);
            exibirOffHand(5,  "Cetro Sagrado",      "[P]","Reduz 1 rodada efeitos negativos em você.",                   20000);
            exibirOffHand(6,  "Tomo de Feitiços",  "[L]","+30 Mana; restaura ao fim de todo combate.",                  15000);
            exibirOffHand(7,  "Olho dos Segredos",  "[L]","+45 Mana.",                                                   22500);
            exibirOffHand(8,  "Muisec",             "[P]","+2 dados dano básico, +3 ECR, -5 RD.",                        30000);
            exibirOffHand(9,  "Raiz Mestra",        "[P]","Vida máx × (10 × Prof); todas as curas pela metade.",         30000);
            exibirOffHand(10, "Incensário Celeste", "[L]","+5 ECR e +5 EFC adicional.",                                  36000);
            exibirOffHand(11, "Escudo",             "[P]","+2 RD; reduz 1 rodada efeitos debilitantes de mobilidade.",   17500);
            exibirOffHand(12, "Sarcófago",          "[P]","+4 RD.",                                                      20000);
            exibirOffHand(13, "Escudo Vampírico",   "[P]","+2 RD e +2 RdV.",                                             35000);
            exibirOffHand(14, "Quebra-rostos",      "[P]","+2 RD e +1 dado extra em ataques básicos.",                   30000);
            exibirOffHand(15, "Égide Astral",       "[P]","+8 RD; reduz mana máxima em 20.",                             35000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case  1: comprarItem("Tocha",             8000);  break;
                case  2: comprarItem("Brumário",         25000);  break;
                case  3: comprarItem("Bengala Maligna",  16000);  break;
                case  4: comprarItem("Lume Críptico",    30000);  break;
                case  5: comprarItem("Cetro Sagrado",    20000);  break;
                case  6: comprarItem("Tomo de Feitiços", 15000);  break;
                case  7: comprarItem("Olho dos Segredos",22500);  break;
                case  8: comprarItem("Muisec",           30000);  break;
                case  9: comprarItem("Raiz Mestra",      30000);  break;
                case 10: comprarItem("Incensário Celeste",36000); break;
                case 11: comprarItem("Escudo",           17500);  break;
                case 12: comprarItem("Sarcófago",        20000);  break;
                case 13: comprarItem("Escudo Vampírico", 35000);  break;
                case 14: comprarItem("Quebra-rostos",    30000);  break;
                case 15: comprarItem("Égide Astral",     35000);  break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirOffHand(int id, String nome, String prop, String desc, long preco) {
        System.out.printf("[%02d] %-22s %s  %s%n     Preço: %s%n%n",
            id, nome, prop, desc, formatarPreco(preco));
    }

    // ============================================================
    // MUNIÇÕES E ARREMESSÁVEIS — placeholder
    // ============================================================

    static void listarMunicoes() {
        limparTela();
        System.out.println("================================");
        System.out.println("   MUNIÇÕES E ARREMESSÁVEIS");
        System.out.println("================================");
        System.out.println("(Em breve...)");
        pausar();
    }

    // ============================================================
    // ESPECIAIS — placeholder
    // ============================================================

    static void listarEspeciais() {
        limparTela();
        System.out.println("================================");
        System.out.println("           ESPECIAIS");
        System.out.println("================================");
        System.out.println("(Em breve...)");
        pausar();
    }

    // ============================================================
    // MENU VESTIMENTAS
    // ============================================================

    static void menuVestimentas() {
        while (true) {
            limparTela();
            System.out.println("================================");
            System.out.println("          VESTIMENTAS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            System.out.println("[1] Simples");
            System.out.println("[2] Complexas");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1: menuSlotVestimenta("SIMPLES",   "simples");   break;
                case 2: menuSlotVestimenta("COMPLEXAS", "complexas"); break;
                case 0: limparTela(); return;
                default: System.out.println("Opção inválida."); espera(800);
            }
        }
    }

    static void menuSlotVestimenta(String titulo, String tipo) {
        while (true) {
            limparTela();
            System.out.println("================================");
            System.out.println("    VESTIMENTAS " + titulo);
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            System.out.println("[1] Cabeça");
            System.out.println("[2] Peito");
            System.out.println("[3] Pés");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (tipo.equals("simples")) vestimentaSimplesCapeca();
                    else vestimentaComplexaCabeca();
                    break;
                case 2:
                    if (tipo.equals("simples")) vestimentaSimplessPeito();
                    else vestimentaComplexaPeito();
                    break;
                case 3:
                    if (tipo.equals("simples")) vestimentaSimplesPes();
                    else vestimentaComplexaPes();
                    break;
                case 0: return;
                default: System.out.println("Opção inválida."); espera(800);
            }
        }
    }

    // ============================================================
    // VESTIMENTAS SIMPLES
    // ============================================================

    static void vestimentaSimplesCapeca() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println(" VEST. SIMPLES — CABEÇA");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            exibirVestimenta(1,  "Capuz de Mercenário", "[L]","+1 CA; +1 dano armas leves.",                        1500);
            exibirVestimenta(2,  "Capuz de Caçador",    "[L]","+1 CA; +1 acerto longa distância.",                  2500);
            exibirVestimenta(3,  "Capuz de Assassino",  "[L]","+1 CA; +1 Furtividade.",                             3000);
            exibirVestimenta(4,  "Capuz Real",          "[L]","+1 CA; +1 Acrobacia.",                               3000);
            exibirVestimenta(5,  "Capote de Erudito",   "[L]","+1 CA; +3 PdM.",                                     3000);
            exibirVestimenta(6,  "Capote de Clérigo",   "[L]","+1 CA; +3 dano armas pesadas.",                      4500);
            exibirVestimenta(7,  "Capote de Mago",      "[L]","+1 CA; +3 dano armas de mago.",                      3500);
            exibirVestimenta(8,  "Capote Real",         "[L]","+1 CA; +1 RCM.",                                     4500);
            exibirVestimenta(9,  "Elmo de Soldado",     "[P]","+2 CA; +2 PV.",                                      5000);
            exibirVestimenta(10, "Elmo de Cavaleiro",   "[P]","+2 CA; +1 dano ataques básicos.",                    4500);
            exibirVestimenta(11, "Elmo de Guardião",    "[P]","+3 CA.",                                             7500);
            exibirVestimenta(12, "Elmo Real",           "[P]","+2 CA; +1 RPD.",                                     5000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case  1: comprarItem("Capuz de Mercenário", 1500); break;
                case  2: comprarItem("Capuz de Caçador",    2500); break;
                case  3: comprarItem("Capuz de Assassino",  3000); break;
                case  4: comprarItem("Capuz Real",          3000); break;
                case  5: comprarItem("Capote de Erudito",   3000); break;
                case  6: comprarItem("Capote de Clérigo",   4500); break;
                case  7: comprarItem("Capote de Mago",      3500); break;
                case  8: comprarItem("Capote Real",         4500); break;
                case  9: comprarItem("Elmo de Soldado",     5000); break;
                case 10: comprarItem("Elmo de Cavaleiro",   4500); break;
                case 11: comprarItem("Elmo de Guardião",    7500); break;
                case 12: comprarItem("Elmo Real",           5000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void vestimentaSimplessPeito() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("  VEST. SIMPLES — PEITO");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            exibirVestimenta(13, "Casaco de Mercenário",  "[L]","+2 CA; +2 dano armas leves.",                     8500);
            exibirVestimenta(14, "Casaco de Caçador",     "[L]","+2 CA; +2 acerto longa distância.",              10000);
            exibirVestimenta(15, "Casaco de Assassino",   "[L]","+2 CA; +2 Furtividade.",                         12500);
            exibirVestimenta(16, "Casaco Real",           "[L]","+2 CA; +2 Acrobacia.",                           12500);
            exibirVestimenta(17, "Robe de Erudito",       "[L]","+2 CA; +5 PdM.",                                 10000);
            exibirVestimenta(18, "Robe de Clérigo",       "[L]","+2 CA; +5 dano armas pesadas.",                  20000);
            exibirVestimenta(19, "Robe de Mago",          "[L]","+2 CA; +5 dano armas de mago.",                  17500);
            exibirVestimenta(20, "Robe Real",             "[L]","+2 CA; +2 RCM.",                                 20000);
            exibirVestimenta(21, "Armadura de Soldado",   "[P]","+3 CA; +5 PV.",                                  20000);
            exibirVestimenta(22, "Armadura de Cavaleiro", "[P]","+3 CA; +2 dano ataques básicos.",                18500);
            exibirVestimenta(23, "Armadura de Guardião",  "[P]","+4 CA.",                                         22500);
            exibirVestimenta(24, "Armadura Real",         "[P]","+3 CA; +2 RPD.",                                 15000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 13: comprarItem("Casaco de Mercenário",   8500); break;
                case 14: comprarItem("Casaco de Caçador",     10000); break;
                case 15: comprarItem("Casaco de Assassino",   12500); break;
                case 16: comprarItem("Casaco Real",           12500); break;
                case 17: comprarItem("Robe de Erudito",       10000); break;
                case 18: comprarItem("Robe de Clérigo",       20000); break;
                case 19: comprarItem("Robe de Mago",          17500); break;
                case 20: comprarItem("Robe Real",             20000); break;
                case 21: comprarItem("Armadura de Soldado",   20000); break;
                case 22: comprarItem("Armadura de Cavaleiro", 18500); break;
                case 23: comprarItem("Armadura de Guardião",  22500); break;
                case 24: comprarItem("Armadura Real",         15000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void vestimentaSimplesPes() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("   VEST. SIMPLES — PÉS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            exibirVestimenta(25, "Sapatos de Mercenário", "[L]","+1 CA; +1 dano armas leves.",                   1500);
            exibirVestimenta(26, "Sapatos de Caçador",    "[L]","+1 CA; +1 acerto longa distância.",             2500);
            exibirVestimenta(27, "Sapatos de Assassino",  "[L]","+1 CA; +2 Furtividade.",                        6500);
            exibirVestimenta(28, "Sapatos Reais",         "[L]","+1 CA; +2 Acrobacia.",                          6500);
            exibirVestimenta(29, "Sandálias de Erudito",  "[L]","+1 CA; +2 PdM.",                               2000);
            exibirVestimenta(30, "Sandálias de Clérigo",  "[L]","+1 CA; +2 dano armas pesadas.",                3500);
            exibirVestimenta(31, "Sandálias de Mago",     "[L]","+1 CA; +2 dano armas de mago.",                3000);
            exibirVestimenta(32, "Sandálias Reais",       "[L]","+1 CA; +1 RCM.",                               8000);
            exibirVestimenta(33, "Botas de Soldado",      "[P]","+2 CA; +1 PV.",                                4000);
            exibirVestimenta(34, "Botas de Cavaleiro",    "[P]","+2 CA; +1 dano ataques básicos.",              4500);
            exibirVestimenta(35, "Botas de Guardião",     "[P]","+3 CA.",                                       7500);
            exibirVestimenta(36, "Botas Reais",           "[P]","+2 CA; +1 RPD.",                               5000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 25: comprarItem("Sapatos de Mercenário", 1500); break;
                case 26: comprarItem("Sapatos de Caçador",    2500); break;
                case 27: comprarItem("Sapatos de Assassino",  6500); break;
                case 28: comprarItem("Sapatos Reais",         6500); break;
                case 29: comprarItem("Sandálias de Erudito",  2000); break;
                case 30: comprarItem("Sandálias de Clérigo",  3500); break;
                case 31: comprarItem("Sandálias de Mago",     3000); break;
                case 32: comprarItem("Sandálias Reais",       8000); break;
                case 33: comprarItem("Botas de Soldado",      4000); break;
                case 34: comprarItem("Botas de Cavaleiro",    4500); break;
                case 35: comprarItem("Botas de Guardião",     7500); break;
                case 36: comprarItem("Botas Reais",           5000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirVestimenta(int id, String nome, String prop, String desc, long preco) {
        System.out.printf("[%02d] %-28s %s  %s  Preço: %s%n",
            id, nome, prop, desc, formatarPreco(preco));
    }

    // ============================================================
    // VESTIMENTAS COMPLEXAS
    // ============================================================

    static void vestimentaComplexaCabeca() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println(" VEST. COMPLEXAS — CABEÇA");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            exibirVestimenta(37, "Capuz de Espreitador",        "[L]","+2 CA; +3 dano arma [M].",                   12000);
            exibirVestimenta(38, "Capuz Inferial",              "[L]","+2 CA; +2 Furtividade.",                     17500);
            exibirVestimenta(39, "Capuz Espectral",             "[L]","+2 CA; +2 roubo de vida.",                   20000);
            exibirVestimenta(40, "Capuz do Andarilho da Névoa", "[L]","+1 CA; +4 Furtividade.",                     27500);
            exibirVestimenta(41, "Capuz da Tenacidade",         "[L]","+2 CA; +1.5m deslocamento.",                 18000);
            exibirVestimenta(42, "Capote de Druida",            "[L]","+2 CA; +1 Cura.",                            22000);
            exibirVestimenta(43, "Capote Malévolo",             "[L]","+2 CA; +2 RPD.",                             17500);
            exibirVestimenta(44, "Capote Sectário",             "[L]","+2 CA; ECR = metade da proficiência.",       25000);
            exibirVestimenta(45, "Capote Feérico",              "[L]","+2 CA; +2 RCM.",                             22500);
            exibirVestimenta(46, "Capote da Pureza",            "[L]","+1 CA; EFC = metade da proficiência.",       25000);
            exibirVestimenta(47, "Elmo de Guarda-tumbas",       "[P]","+3 CA; +5 PV.",                             22000);
            exibirVestimenta(48, "Elmo Demônio",                "[P]","+3 CA; +3 dano ataques básicos.",            20000);
            exibirVestimenta(49, "Elmo Judicante",              "[P]","+3 CA; +2 RD.",                              26000);
            exibirVestimenta(50, "Elmo de Tecelão do Crepúsculo","[P]","+1 CA; +1 rodada nos efeitos Lento/Imóvel.",24000);
            exibirVestimenta(51, "Elmo da Bravura",             "[P]","+4 CA.",                                     30000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 37: comprarItem("Capuz de Espreitador",         12000); break;
                case 38: comprarItem("Capuz Inferial",               17500); break;
                case 39: comprarItem("Capuz Espectral",              20000); break;
                case 40: comprarItem("Capuz do Andarilho da Névoa",  27500); break;
                case 41: comprarItem("Capuz da Tenacidade",          18000); break;
                case 42: comprarItem("Capote de Druida",             22000); break;
                case 43: comprarItem("Capote Malévolo",              17500); break;
                case 44: comprarItem("Capote Sectário",              25000); break;
                case 45: comprarItem("Capote Feérico",               22500); break;
                case 46: comprarItem("Capote da Pureza",             25000); break;
                case 47: comprarItem("Elmo de Guarda-tumbas",        22000); break;
                case 48: comprarItem("Elmo Demônio",                 20000); break;
                case 49: comprarItem("Elmo Judicante",               26000); break;
                case 50: comprarItem("Elmo de Tecelão do Crepúsculo",24000); break;
                case 51: comprarItem("Elmo da Bravura",              30000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void vestimentaComplexaPeito() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("  VEST. COMPLEXAS — PEITO");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            exibirVestimenta(52, "Casaco de Espreitador",          "[L]","+3 CA; +5 dano arma [M].",                25000);
            exibirVestimenta(53, "Casaco Inferial",                "[L]","+3 CA; +3 Furtividade.",                  30000);
            exibirVestimenta(54, "Casaco Espectral",               "[L]","+3 CA; +4 roubo de vida.",                35000);
            exibirVestimenta(55, "Casaco do Andarilho da Névoa",   "[L]","+2 CA; +5 Furtividade.",                  32500);
            exibirVestimenta(56, "Casaco da Tenacidade",           "[L]","+3 CA; +1.5m deslocamento.",              25000);
            exibirVestimenta(57, "Robe de Druida",                 "[L]","+3 CA; +3 Cura.",                         40000);
            exibirVestimenta(58, "Robe Malévolo",                  "[L]","+3 CA; +4 RPD.",                          30000);
            exibirVestimenta(59, "Robe Sectário",                  "[L]","+3 CA; ECR = metade da proficiência.",    37500);
            exibirVestimenta(60, "Robe Feérico",                   "[L]","+3 CA; +3 RCM.",                          42000);
            exibirVestimenta(61, "Robe da Pureza",                 "[L]","+2 CA; EFC = metade da proficiência.",    42500);
            exibirVestimenta(62, "Armadura de Guarda-tumbas",      "[P]","+4 CA; +10 PV.",                          40000);
            exibirVestimenta(63, "Armadura Demônia",               "[P]","+4 CA; +5 dano ataques básicos.",         38000);
            exibirVestimenta(64, "Armadura Judicante",             "[P]","+4 CA; +4 RD.",                           50000);
            exibirVestimenta(65, "Armadura de Tecelão do Crepúsculo","[P]","+2 CA; +1 rodada nos efeitos Lento/Imóvel.",40000);
            exibirVestimenta(66, "Armadura da Bravura",            "[P]","+5 CA.",                                  50000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 52: comprarItem("Casaco de Espreitador",            25000); break;
                case 53: comprarItem("Casaco Inferial",                  30000); break;
                case 54: comprarItem("Casaco Espectral",                 35000); break;
                case 55: comprarItem("Casaco do Andarilho da Névoa",     32500); break;
                case 56: comprarItem("Casaco da Tenacidade",             25000); break;
                case 57: comprarItem("Robe de Druida",                   40000); break;
                case 58: comprarItem("Robe Malévolo",                    30000); break;
                case 59: comprarItem("Robe Sectário",                    37500); break;
                case 60: comprarItem("Robe Feérico",                     42000); break;
                case 61: comprarItem("Robe da Pureza",                   42500); break;
                case 62: comprarItem("Armadura de Guarda-tumbas",        40000); break;
                case 63: comprarItem("Armadura Demônia",                 38000); break;
                case 64: comprarItem("Armadura Judicante",               50000); break;
                case 65: comprarItem("Armadura de Tecelão do Crepúsculo",40000); break;
                case 66: comprarItem("Armadura da Bravura",              50000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void vestimentaComplexaPes() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("  VEST. COMPLEXAS — PÉS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();
            exibirVestimenta(67, "Sapatos de Espreitador",        "[L]","+2 CA; +2 dano arma [M].",                 11000);
            exibirVestimenta(68, "Sapatos Inferiais",             "[L]","+2 CA; +3 Furtividade.",                   19000);
            exibirVestimenta(69, "Sapatos Espectrais",            "[L]","+2 CA; +1 roubo de vida.",                 18000);
            exibirVestimenta(70, "Sapatos do Andarilho da Névoa", "[L]","+1 CA; +5 Furtividade.",                   29500);
            exibirVestimenta(71, "Sapatos da Tenacidade",         "[L]","+2 CA; +3m deslocamento.",                 32000);
            exibirVestimenta(72, "Sandálias de Druida",           "[L]","+2 CA; +2 Cura.",                          24500);
            exibirVestimenta(73, "Sandálias Malévolas",           "[L]","+2 CA; +1 RPD.",                           16000);
            exibirVestimenta(74, "Sandálias Sectárias",           "[L]","+2 CA; ECR = metade da proficiência.",     25000);
            exibirVestimenta(75, "Sandálias Feéricas",            "[L]","+2 CA; +2 RCM.",                           21000);
            exibirVestimenta(76, "Sandálias da Pureza",           "[L]","+1 CA; EFC = metade da proficiência.",     30000);
            exibirVestimenta(77, "Botas de Guarda-tumbas",        "[P]","+3 CA; +4 PV.",                            21000);
            exibirVestimenta(78, "Botas Demônias",                "[P]","+3 CA; +4 dano ataques básicos.",          21500);
            exibirVestimenta(79, "Botas Judicantes",              "[P]","+3 CA; +2 RD.",                            26000);
            exibirVestimenta(80, "Botas de Tecelão do Crepúsculo","[P]","+1 CA; +1 rodada nos efeitos Lento/Imóvel.",22500);
            exibirVestimenta(81, "Botas da Bravura",              "[P]","+4 CA.",                                   30000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case 67: comprarItem("Sapatos de Espreitador",         11000); break;
                case 68: comprarItem("Sapatos Inferiais",              19000); break;
                case 69: comprarItem("Sapatos Espectrais",             18000); break;
                case 70: comprarItem("Sapatos do Andarilho da Névoa",  29500); break;
                case 71: comprarItem("Sapatos da Tenacidade",          32000); break;
                case 72: comprarItem("Sandálias de Druida",            24500); break;
                case 73: comprarItem("Sandálias Malévolas",            16000); break;
                case 74: comprarItem("Sandálias Sectárias",            25000); break;
                case 75: comprarItem("Sandálias Feéricas",             21000); break;
                case 76: comprarItem("Sandálias da Pureza",            30000); break;
                case 77: comprarItem("Botas de Guarda-tumbas",         21000); break;
                case 78: comprarItem("Botas Demônias",                 21500); break;
                case 79: comprarItem("Botas Judicantes",               26000); break;
                case 80: comprarItem("Botas de Tecelão do Crepúsculo", 22500); break;
                case 81: comprarItem("Botas da Bravura",               30000); break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    // ============================================================
    // ACESSÓRIOS
    // ============================================================

    static void listarAcessorios() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("          ACESSÓRIOS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirAcessorio(1,  "Bracelete-imã",               "[L]",1, "Recupera arma arremessada ao fim da rodada seguinte como ação bônus.",                                  20000);
            exibirAcessorio(2,  "Empunhadura de Precisão",     "[L]",1, "+3 acerto CaC; -1 chance de crítico.",                                                                   30000);
            exibirAcessorio(3,  "Extensor de Envergadura",     "[P]",2, "+1.5m de alcance em ataques corpo-a-corpo.",                                                             60000);
            exibirAcessorio(4,  "Grimório Amplificador",       "[L]",1, "+1 dado de dano em magias Básicas (passivo).",                                                           42500);
            exibirAcessorio(5,  "Grimório de Condução Elementar","[P]",2,"+1 dado de dano em magias Elementares (passivo).",                                                      47500);
            exibirAcessorio(6,  "Anel do Vigor",               "[L]",1, "+2 Força (pode exceder limite); +2×Prof de PV máx.",                                                    70000);
            exibirAcessorio(7,  "Anel da Velocidade",          "[L]",1, "+2 Destreza (pode exceder limite); +3m deslocamento.",                                                   52500);
            exibirAcessorio(8,  "Amuleto Élfico",              "[P]",2, "+30 mana máxima; -6 custo de mana.",                                                                    100000);
            exibirAcessorio(9,  "Anel de Prata",               "[L]",1, "+5 PV máx.",                                                                                              2000);
            exibirAcessorio(10, "Anel de Ouro",                "[L]",1, "+5 PdM máx.",                                                                                             2000);
            exibirAcessorio(11, "Colar de Prata",              "[L]",1, "+10 PV máx.",                                                                                             5000);
            exibirAcessorio(12, "Colar de Ouro",               "[L]",1, "+10 PdM máx.",                                                                                            5000);
            exibirAcessorio(13, "Bracelete de Prata",          "[P]",1, "+20 PV máx.",                                                                                            10000);
            exibirAcessorio(14, "Bracelete de Ouro",           "[L]",1, "+20 PdM máx.",                                                                                           10000);
            exibirAcessorio(15, "Asas Etéreas",                "[P]",2, "Voo; ignora Terreno Difícil e Encurralado; +6m deslocamento.",                                          150000);
            exibirAcessorio(16, "Amuleto da Resistência",      "[P]",2, "Reduz 1 rodada os efeitos debilitantes de mobilidade.",                                                  75000);
            exibirAcessorio(17, "Reforço de Armadura",         "[P]",2, "+1 CA para cada vestimenta equipada.",                                                                  120000);
            exibirAcessorio(18, "Brincos da Imortalidade",     "[L]",1, "1×/sessão: nega a morte, ficando com 1 PV.",                                                            100000);
            exibirAcessorio(19, "Pingente Sanguinário",        "[L]",1, "Roubo de vida = Proficiência.",                                                                          85000);
            exibirAcessorio(20, "Bolsa Comum",                 "[L]",1, "+5 carga máxima.",                                                                                       10000);
            exibirAcessorio(21, "Bolsa Aprimorada",            "[L]",1, "+10 carga máxima.",                                                                                      20000);
            exibirAcessorio(22, "Superbolsa",                  "[P]",2, "+20 carga máxima.",                                                                                      40000);
            exibirAcessorio(23, "Cinto de Poções",             "[L]",1, "Armazena 5 poções sem ocupar inventário.",                                                               30000);
            exibirAcessorio(24, "Chapéu de Mago Especialista", "[L]",1, "Domina +1 magia Elementar.",                                                                            100000);
            exibirAcessorio(25, "Anel de Especialista em Combate","[L]",1,"+2 Classe de Dificuldade.",                                                                            40000);
            exibirAcessorio(26, "Capa de Sovngarde",           "[L]",1, "+2 no limite máximo de Inteligência.",                                                                   50000);
            exibirAcessorio(27, "Capa de Darseyann",           "[L]",1, "+2 no limite máximo de Força.",                                                                          50000);
            exibirAcessorio(28, "Capa de Goldengate",          "[L]",1, "+2 no limite máximo de Sabedoria.",                                                                      50000);
            exibirAcessorio(29, "Capa de Alkanneir",           "[L]",1, "+2 no limite máximo de Destreza.",                                                                       50000);
            exibirAcessorio(30, "Capa de Bridgestone",         "[P]",2, "+2 no limite máximo de Constituição.",                                                                   50000);
            exibirAcessorio(31, "Capa de Hella",               "[P]",2, "+2 no limite máximo de Carisma.",                                                                        50000);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case  1: comprarItem("Bracelete-imã",                   20000); break;
                case  2: comprarItem("Empunhadura de Precisão",         30000); break;
                case  3: comprarItem("Extensor de Envergadura",         60000); break;
                case  4: comprarItem("Grimório Amplificador",           42500); break;
                case  5: comprarItem("Grimório de Condução Elementar",  47500); break;
                case  6: comprarItem("Anel do Vigor",                   70000); break;
                case  7: comprarItem("Anel da Velocidade",              52500); break;
                case  8: comprarItem("Amuleto Élfico",                 100000); break;
                case  9: comprarItem("Anel de Prata",                   2000);  break;
                case 10: comprarItem("Anel de Ouro",                    2000);  break;
                case 11: comprarItem("Colar de Prata",                  5000);  break;
                case 12: comprarItem("Colar de Ouro",                   5000);  break;
                case 13: comprarItem("Bracelete de Prata",             10000);  break;
                case 14: comprarItem("Bracelete de Ouro",              10000);  break;
                case 15: comprarItem("Asas Etéreas",                  150000);  break;
                case 16: comprarItem("Amuleto da Resistência",         75000);  break;
                case 17: comprarItem("Reforço de Armadura",           120000);  break;
                case 18: comprarItem("Brincos da Imortalidade",       100000);  break;
                case 19: comprarItem("Pingente Sanguinário",           85000);  break;
                case 20: comprarItem("Bolsa Comum",                   10000);   break;
                case 21: comprarItem("Bolsa Aprimorada",              20000);   break;
                case 22: comprarItem("Superbolsa",                    40000);   break;
                case 23: comprarItem("Cinto de Poções",               30000);   break;
                case 24: comprarItem("Chapéu de Mago Especialista",  100000);   break;
                case 25: comprarItem("Anel de Especialista em Combate",40000);  break;
                case 26: comprarItem("Capa de Sovngarde",             50000);   break;
                case 27: comprarItem("Capa de Darseyann",             50000);   break;
                case 28: comprarItem("Capa de Goldengate",            50000);   break;
                case 29: comprarItem("Capa de Alkanneir",             50000);   break;
                case 30: comprarItem("Capa de Bridgestone",           50000);   break;
                case 31: comprarItem("Capa de Hella",                 50000);   break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirAcessorio(int id, String nome, String prop, int peso, String desc, long preco) {
        System.out.printf("[%02d] %-32s %s  Peso: %d%n     %s%n     Preço: %s%n%n",
            id, nome, prop, peso, desc, formatarPreco(preco));
    }

    // ============================================================
    // ITENS
    // ============================================================

    static void listarItens() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("            ITENS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirItem(1,  "Poção de Fúria",          "[L]",0.5, "+1 dado de dano na rodada atual.",                                                                   3000);
            exibirItem(2,  "Poção de Cura",            "[L]",0.5, "Restaura +20 PV. (Com PV cheio: +5 PdM temporário.)",                                               9000);
            exibirItem(3,  "Poção de Mana",            "[L]",0.5, "Restaura +20 mana. (Com mana cheia: +10 PdM temporário.)",                                          8500);
            exibirItem(4,  "Poção Venenosa",           "[L]",0.5, "Causa Envenenado a um inimigo.",                                                                    10000);
            exibirItem(5,  "Poção de Invisibilidade",  "[L]",0.5, "Garante sucesso em um teste de Furtividade.",                                                       15000);
            exibirItem(6,  "Poção Congelante",         "[L]",0.5, "Causa Lento a um inimigo por 1 rodada.",                                                            10000);
            exibirItem(7,  "Poção de Foco",            "[L]",0.5, "+3 acerto na rodada atual.",                                                                        12500);
            exibirItem(8,  "Poção Vampírica",          "[L]",0.5, "+½ Proficiência como roubo de vida na rodada atual.",                                               15000);
            exibirItem(9,  "Poção Calmante",           "[L]",0.5, "Restaura +4 sanidade.",                                                                             10000);
            exibirItem(10, "Poção de Resistência",     "[L]",0.5, "+5 RD na rodada atual.",                                                                            17500);
            exibirItem(11, "Guisado de Carneiro",      "[L]",1.0, "+dano igual à qtd. de dados de dano do ataque, pelo combate atual.",                               25000);
            exibirItem(12, "Guisado de Carne",         "[L]",1.0, "+dano igual ao dobro da qtd. de dados de dano do ataque, pelo combate atual.",                     50000);
            exibirItem(13, "Pargo-Neblina",            "[L]",1.0, "+5 dano e +½ Prof. (arred. p/ cima) como roubo de vida no combate atual.",                         32500);
            exibirItem(14, "Kit-médico",               "[P]",2.0, "Cura aliado em +20 PV (teste de Cura); +3 em Cura para estabilizar.",                              15000);
            exibirItem(15, "Kit-médico Avançado",      "[P]",2.0, "Cura aliado em +40 PV (teste de Cura); +6 em Cura para estabilizar.",                              27500);
            exibirItem(16, "Estabilizador Vital",      "[P]",2.0, "Estabiliza aliado sem teste de perícia.",                                                           50000);
            exibirItem(17, "Porco Assado",             "[L]",1.0, "+1 roubo de vida no combate atual.",                                                                 5000);
            exibirItem(18, "Omelete de Porco",         "[L]",1.0, "Recarrega todas as habilidades (geral).",                                                           14500);
            exibirItem(19, "Sopa de Repolho",          "[L]",1.0, "+40 PV ao fim de um combate.",                                                                      8500);
            exibirItem(20, "Salada de Feijão",         "[L]",1.0, "+5 PV temporário.",                                                                                 2500);
            exibirItem(21, "Frango Assado",            "[L]",1.0, "+10 PV temporário.",                                                                                 6000);
            exibirItem(22, "Sanduíche de Cabra",       "[L]",1.0, "+25 PV temporário.",                                                                                10000);
            exibirItem(23, "Torta de Porco",           "[L]",1.0, "XP ganho ao abater inimigos +Prof×Prof.",                                                           7000);
            exibirItem(24, "Super Poção de Cura",      "[L]",1.0, "Restaura +40 PV.",                                                                                 17500);
            exibirItem(25, "Super Poção Venenosa",     "[L]",1.0, "Causa Envenenado; dura 1 rodada a mais.",                                                           14500);
            exibirItem(26, "Super Poção Congelante",   "[L]",1.0, "Causa Lento; dura 1 rodada a mais.",                                                               19000);
            exibirItem(27, "Super Poção de Fúria",     "[L]",1.0, "+2 dados de dano na rodada atual.",                                                                  6000);
            exibirItem(28, "Super Poção Vampírica",    "[L]",1.0, "+Prof como roubo de vida na rodada atual.",                                                         30000);
            exibirItem(29, "Super Poção Calmante",     "[L]",1.0, "Restaura +8 sanidade.",                                                                             19000);
            exibirItem(30, "Super Poção de Resistência","[L]",1.0,"+10 RD na rodada atual.",                                                                           30000);
            exibirItem(31, "Super Poção de Foco",      "[L]",1.0, "+6 acerto na rodada atual.",                                                                        24500);
            exibirItem(32, "Apito Mágico",             "[L]",1.0, "Chama sua montaria de qualquer lugar (salvo incapacitação).",                                        7500);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case  1: comprarItem("Poção de Fúria",            3000);  break;
                case  2: comprarItem("Poção de Cura",             9000);  break;
                case  3: comprarItem("Poção de Mana",             8500);  break;
                case  4: comprarItem("Poção Venenosa",           10000);  break;
                case  5: comprarItem("Poção de Invisibilidade",  15000);  break;
                case  6: comprarItem("Poção Congelante",         10000);  break;
                case  7: comprarItem("Poção de Foco",            12500);  break;
                case  8: comprarItem("Poção Vampírica",          15000);  break;
                case  9: comprarItem("Poção Calmante",           10000);  break;
                case 10: comprarItem("Poção de Resistência",     17500);  break;
                case 11: comprarItem("Guisado de Carneiro",      25000);  break;
                case 12: comprarItem("Guisado de Carne",         50000);  break;
                case 13: comprarItem("Pargo-Neblina",            32500);  break;
                case 14: comprarItem("Kit-médico",               15000);  break;
                case 15: comprarItem("Kit-médico Avançado",      27500);  break;
                case 16: comprarItem("Estabilizador Vital",      50000);  break;
                case 17: comprarItem("Porco Assado",              5000);  break;
                case 18: comprarItem("Omelete de Porco",         14500);  break;
                case 19: comprarItem("Sopa de Repolho",           8500);  break;
                case 20: comprarItem("Salada de Feijão",          2500);  break;
                case 21: comprarItem("Frango Assado",             6000);  break;
                case 22: comprarItem("Sanduíche de Cabra",       10000);  break;
                case 23: comprarItem("Torta de Porco",            7000);  break;
                case 24: comprarItem("Super Poção de Cura",      17500);  break;
                case 25: comprarItem("Super Poção Venenosa",     14500);  break;
                case 26: comprarItem("Super Poção Congelante",   19000);  break;
                case 27: comprarItem("Super Poção de Fúria",      6000);  break;
                case 28: comprarItem("Super Poção Vampírica",    30000);  break;
                case 29: comprarItem("Super Poção Calmante",     19000);  break;
                case 30: comprarItem("Super Poção de Resistência",30000); break;
                case 31: comprarItem("Super Poção de Foco",      24500);  break;
                case 32: comprarItem("Apito Mágico",              7500);  break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirItem(int id, String nome, String prop, double peso, String desc, long preco) {
        System.out.printf("[%02d] %-26s %s  Peso: %.1f%n     %s%n     Preço: %s%n%n",
            id, nome, prop, peso, desc, formatarPreco(preco));
    }

    // ============================================================
    // MONTARIAS
    // ============================================================

    static void listarMontarias() {
        limparTela();
        while (true) {
            System.out.println("================================");
            System.out.println("          MONTARIAS");
            System.out.println("================================");
            mostrarSaldo();
            System.out.println();

            exibirMontaria(1,  "Cavalo",                "[V]",     "15m",1000,"CaC",  "2d6 Impac.","+5",  50000);
            exibirMontaria(2,  "Cavalo Blindado",       "[D]",     "12m",1500,"CaC",  "2d8 Impac.","-",   60000);
            exibirMontaria(3,  "Boi de Transporte",     "[C]",     "6m", 2500,"CaC",  "2d4 Impac.","+20", 50000);
            exibirMontaria(4,  "Veado-gigante",         "[C]",     "9m", 1000,"CaC",  "2d6 Impac.","+10", 50000);
            exibirMontaria(5,  "Alce",                  "[V][C]",  "15m",1500,"CaC",  "2d8 Impac.","+15", 85000);
            exibirMontaria(6,  "Lobo-vil",              "[V]",     "15m",1200,"CaC",  "2d8 Perf.", "-",   60000);
            exibirMontaria(7,  "Javali-vil Selado",     "[V][C]",  "12m",2000,"CaC",  "2d6 Impac.","+20", 75000);
            exibirMontaria(8,  "Carneiro Selado",       "[C][D]",  "9m", 3000,"CaC",  "2d4 Impac.","+20", 15000);
            exibirMontaria(9,  "Lobo-cinzento Selado",  "[V]",     "15m",1000,"CaC",  "2d8 Perf.", "-",   10000);
            exibirMontaria(10, "Coruja Mística Selada", "[V]",     "18m", 700,"CaC",  "2d4 Perf.", "-",   17500);
            exibirMontaria(11, "Urso-vil Selado",       "[C][D]",  "9m", 4000,"CaC",  "2d6 Perf.", "+25", 25000);
            exibirMontaria(12, "Urso-polar de Elite",   "[D][C][B]","9m", 4000,"CaC",  "2d6 Perf.", "+25", 50000);
            exibirMontaria(13, "Javali Selvagem de Elite","[D][C][B]","12m",2500,"CaC","2d6 Impac.","+25", 32500);
            exibirMontaria(14, "Carneiro Selvagem de Elite","[C][D]","9m", 4500,"CaC", "2d4 Impac.","+25", 15000);
            exibirMontaria(15, "Lobo-cinzento de Elite","[V]",     "18m",1500,"CaC",  "2d8 Perf.", "-",   30000);
            exibirMontaria(16, "Coruja Mística de Elite","[V]",    "21m",1000,"CaC",  "2d4 Perf.", "-",   30000);
            exibirMontaria(17, "Mamute de Comando",     "[D][C][B][S]","6m",10000,"CaC","2d8 Impac.","+50",2550000);
            exibirMontaria(18, "Coruja Divina",         "[V]",     "24m",1500,"CaC",  "2d4 Perf.", "-",   14500);
            exibirMontaria(19, "Pantera Negra",         "[V]",     "21m",2000,"CaC",  "2d8 Perf.", "-",    8500);
            exibirMontaria(20, "Cavalo Gigante",        "[D][S]",  "15m",2500,"CaC",  "2d6 Impac.","+10",  2500);
            exibirMontaria(21, "Husky da Neve",         "[V]",     "21m",1500,"CaC",  "2d8 Perf.", "-",    6000);
            exibirMontaria(22, "Rinoceronte de Batalha","[B]",     "9m", 4500,"CaC",  "2d10 Perf.","-",   10000);
            exibirMontaria(23, "Carruagem de Torre",    "[B]",     "6m", 5000,"-",    "-",         "+10",1000000);
            exibirMontaria(24, "Águia de Batalha",      "[V][B]",  "24m",2500,"CaC",  "2d6 Perf.", "-",   17500);
            exibirMontaria(25, "Besouro do Colosso",    "[V][B][C][S]","21m",3500,"CaC","2d6 Perf.","+25",14500);
            exibirMontaria(26, "Behemoth",              "[B]",     "9m", 4000,"9m",   "2d12 Impac.","-",  19000);
            exibirMontaria(27, "Ent Ancião",            "[B]",     "6m", 3000,"3m",   "2d4 Cort.", "-",    6000);
            exibirMontaria(28, "Golias Devorador",      "[B]",     "9m", 3500,"CaC",  "2d6 Perf.", "-",   30000);
            exibirMontaria(29, "Bastião Errante",       "[B][S]",  "6m", 3000,"12m",  "2d12 Perf.","-",  19000);
            exibirMontaria(30, "Juggernaut",            "[B][S]",  "6m",10000,"CaC",  "2d12 Impac.","-",2550000);
            exibirMontaria(31, "Besouro Falange",       "[B][S]",  "6m", 5000,"CaC",  "2d10 Perf.","-",  24500);

            System.out.println("[0] Voltar");
            System.out.print("\nDigite o ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            switch (id) {
                case  1: comprarItem("Cavalo",                     50000);  break;
                case  2: comprarItem("Cavalo Blindado",            60000);  break;
                case  3: comprarItem("Boi de Transporte",          50000);  break;
                case  4: comprarItem("Veado-gigante",              50000);  break;
                case  5: comprarItem("Alce",                       85000);  break;
                case  6: comprarItem("Lobo-vil",                   60000);  break;
                case  7: comprarItem("Javali-vil Selado",          75000);  break;
                case  8: comprarItem("Carneiro Selado",            15000);  break;
                case  9: comprarItem("Lobo-cinzento Selado",       10000);  break;
                case 10: comprarItem("Coruja Mística Selada",      17500);  break;
                case 11: comprarItem("Urso-vil Selado",            25000);  break;
                case 12: comprarItem("Urso-polar de Elite",        50000);  break;
                case 13: comprarItem("Javali Selvagem de Elite",   32500);  break;
                case 14: comprarItem("Carneiro Selvagem de Elite", 15000);  break;
                case 15: comprarItem("Lobo-cinzento de Elite",     30000);  break;
                case 16: comprarItem("Coruja Mística de Elite",    30000);  break;
                case 17: comprarItem("Mamute de Comando",        2550000);  break;
                case 18: comprarItem("Coruja Divina",              14500);  break;
                case 19: comprarItem("Pantera Negra",               8500);  break;
                case 20: comprarItem("Cavalo Gigante",              2500);  break;
                case 21: comprarItem("Husky da Neve",               6000);  break;
                case 22: comprarItem("Rinoceronte de Batalha",     10000);  break;
                case 23: comprarItem("Carruagem de Torre",        1000000); break;
                case 24: comprarItem("Águia de Batalha",           17500);  break;
                case 25: comprarItem("Besouro do Colosso",         14500);  break;
                case 26: comprarItem("Behemoth",                   19000);  break;
                case 27: comprarItem("Ent Ancião",                  6000);  break;
                case 28: comprarItem("Golias Devorador",           30000);  break;
                case 29: comprarItem("Bastião Errante",            19000);  break;
                case 30: comprarItem("Juggernaut",               2550000);  break;
                case 31: comprarItem("Besouro Falange",            24500);  break;
                case  0: limparTela(); return;
                default: System.out.println("ID inválido."); espera(800); limparTela();
            }
        }
    }

    static void exibirMontaria(int id, String nome, String prop, String desloc, int pv, String alcance, String dano, String carga, long preco) {
        System.out.printf("[%02d] %-30s %s%n     Desloc: %-5s  PV: %-5d  Alc: %-5s  Dano: %-14s  Carga: %-5s  Preço: %s%n%n",
            id, nome, prop, desloc, pv, alcance, dano, carga, formatarPreco(preco));
    }

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(String[] args) {
        solicitarSaldo();
        abrirLoja();
        menuPrincipal();
    }
}
