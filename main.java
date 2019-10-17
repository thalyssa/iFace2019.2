import java.util.Scanner;
import static java.lang.System.exit;

public class main {

    public static int[] userIDs = new int[100];
    static String[] userNames = new String[100];
    static String[] userLogins= new String[100];
    static String[] userPasswords = new String[100];
    public static int userActualID; //Id do usuário que está com a sessão aberta
    public static int countUsers = 0;
    public static int[][] friendshipRegister = new int[100][100];

    static String[] communitiesNames = new String[100];
    static String[] communitiesDescriptions = new String[100];
    static int[] communitiesOwners = new int[100];
    public static int countCom = 0;
    public static int[][] communitiesRegister = new int[100][100];

    //USUÁRIOS

    public static void createUser(){
        Scanner input = new Scanner(System.in);
        if(countUsers >= 100){
            System.out.println("Máximo de usuários!");
        }else{
            System.out.println("CRIAÇÃO DE USUÁRIO");
            System.out.println("Por favor, digite um e-mail: ");

            userLogins[countUsers] = input.next();

            System.out.println("Agora digite uma senha: ");
            userPasswords[countUsers] = input.next();

            System.out.println("Por último, digite um nome de usuário: ");
            userNames[countUsers] = input.next();

            userIDs[countUsers] = countUsers;
            countUsers++;
        }
    }

    public static void editProfile(){
        int option;
        String data;
        Scanner board = new Scanner(System.in);

        System.out.println("EDIÇÃO DE PERFIL");
        System.out.println("Dados atuais");
        System.out.println("Login: " + userLogins[userActualID]);
        System.out.println("Senha: " + userPasswords[userActualID]);
        System.out.println("Usuário: " + userNames[userActualID]);
        System.out.println("Favor, escolha o campo que deseja alterar: ");
        System.out.println("1 - Login\n2 - Senha\n3 - Usuário");

        option = board.nextInt();

        switch(option){
            case 1:
                System.out.println("Digite o novo login: ");
                data = board.next();
                userLogins[userActualID] = data;
                System.out.println("Login alterado com sucesso!");
                break;
            case 2:
                System.out.println("Digite a nova senha: ");
                data = board.next();
                userPasswords[userActualID] = data;
                System.out.println("Senha alterada com sucesso!");
                break;
            case 3:
                System.out.println("Digite o novo usuário: ");
                data = board.next();
                userNames[userActualID] = data;
                System.out.println("Usuário alterado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void deleteProfile(){
        userNames[userActualID] = "-1";
        userLogins[userActualID] = "-1";
        userPasswords[userActualID] = "-1";
        userIDs[userActualID] = -1;
        for(int i=0;i<countCom;i++){
            if(communitiesOwners[i] == userActualID){
                communitiesNames[i] = "-1";
                communitiesDescriptions[i] = "-1";
                communitiesOwners[i] = -1;

            }
        }

        System.out.println("Perfil deletado com sucesso!");
    }

    public static void sendFriendshipRequest(){
        Scanner board = new Scanner(System.in);
        String login;
        int id;

        System.out.println("Digite o login do seu amigo: ");
        login = board.nextLine();

        id = getUserID(login);

        if(id == -1){
            System.out.println("Usuário não encontrado!");
            return;
        }

        friendshipRegister[userActualID][id] = 1;
        System.out.println("Pedido enviado");
    }

    public static void acceptFriendshipRequest(){
        Scanner board = new Scanner(System.in);
        int op = 0;
        for(int i=0;i<100;i++){
            if(friendshipRegister[i][userActualID] == 1){
                System.out.println("Solicitação de amizade de: " + userNames[i] + " deseja aceitar? \n 1 - Sim \n 2 - Não");
                op = board.nextInt();
                if(op == 1){
                    friendshipRegister[userActualID][i] = 1;
                }else{
                    friendshipRegister[i][userActualID] = 0;
                }
            }
        }
    }

    public static void searchProfile(){
        System.out.println("--PERFIL--");
        System.out.println("Nome: " + userNames[userActualID]);
        System.out.println("Login: " + userLogins[userActualID]);
        System.out.println("\n");

        System.out.println("--LISTA DE AMIGOS--");
        for(int i=0;i<100;i++){
            if(friendshipRegister[i][userActualID] == 1 && friendshipRegister[userActualID][i] == 1) {
                System.out.println(userNames[i]);
            }
                System.out.println("\n");
        }

        System.out.println("\n--COMUNIDADES--");
        for(int i=0;i<=countCom;i++){
            if(communitiesOwners[i] == userActualID){
                System.out.println("Nome: " + communitiesNames[i]);
            }
        }
    }


    //COMUNIDADES

    public static void createCommunity(){
        Scanner board = new Scanner(System.in);
        String cName;
        String description;

        System.out.println("CRIAÇÃO DE COMUNIDADE");
        System.out.println("Digite um nome para a comunidade: ");
        communitiesNames[countCom] = board.nextLine();

        System.out.println("Digite uma descrição para a comunidade: ");
        communitiesDescriptions[countCom] = board.nextLine();

        communitiesOwners[countCom] = userActualID;

        countCom++;
    }

    public static void joinCommunity(){
        Scanner keyboard = new Scanner(System.in);
        int id;

        System.out.println("Digite o ID da comunidade");
        id = keyboard.nextInt();

        communitiesRegister[id][userActualID] = 1;

        System.out.println("Você agora é um membro desta comunidade!");

    }

    public static void seeCommunities(){
        for(int i=0;i<countCom;i++){
            System.out.println(communitiesNames[i]);
            System.out.println("Dono: " + userNames[communitiesOwners[i]]);
            System.out.println(communitiesDescriptions[i]);
            System.out.println("ID de ingresso: " + i + "\n");
            getMembersList(i);
        }
    }

    public static void getMembersList(int communityID){
        System.out.println("--MEMBROS--");
        for(int i=0; i<100; i++){
            if(communitiesRegister[communityID][i] == 1){
                System.out.println(userNames[i]+"\n");
            }
        }
    }

    //GERAL

    public static int getUserID(String login){
        for(int i=0;i<countUsers;i++){
            if(login.equals(userLogins[i]) && userIDs[i]!=-1) {
                return userIDs[i];
            }
        }//Fim for
        return -1;
    }

    public static void signIn(){
        Scanner board = new Scanner(System.in);
        String login;
        String password;

        System.out.println("TELA DE LOGIN");
        System.out.println("E-mail: ");
        login = board.nextLine();
        System.out.println("Senha: ");
        password = board.nextLine();

        for(int i=0;i<countUsers;i++){
            if(login.equals(userLogins[i])){
                if(!password.equals(userPasswords[i])){
                    System.out.println("Senha incorreta");
                }else {
                    //Exibe a tela de login e armazena o ID do usuário para usar nas operações como: Envio de mensagens, edição de perfil, etc.
                    userActualID = i;
                    System.out.println("\n");
                    userLoggedScreen();
                    return;
                }
            }
        }//Fim do for

        System.out.println("ERRO! Usuário não encontrado\nPor favor, verifique suas informações");
        exit(0);
    }

    public static void initialScreen(){
        System.out.println("\n\n\n");
        System.out.println("Bem vindo ao iFace!\nPor favor, escolha uma opção: ");
        System.out.println("1. Registrar-se");
        System.out.println("2. Login");
        System.out.println("3. Sair");
    }

    public static void userLoggedScreen(){
        Scanner keyboard = new Scanner(System.in);
        int option;

        System.out.println("Bem vindo ao iFace, " + userNames[userActualID] + "!\n Escolha o que deseja fazer: ");

        System.out.println("1 - Editar perfil");
        System.out.println("2 - Adicionar amigo");
        System.out.println("3 - Ver pedidos de amizade");
        System.out.println("4 - Criar comunidade");
        System.out.println("5 - Ver comunidades");
        System.out.println("6 - Se juntar a uma comunidade");
        System.out.println("7 - Verificar informações do perfil");
        System.out.println("8 - Deletar conta");
        System.out.println("9 - Sair");

        option = keyboard.nextInt();

        switch (option){
            case 1:
                editProfile();
                break;
            case 2:
                sendFriendshipRequest();
                break;
            case 3:
                acceptFriendshipRequest();
                break;
            case 4:
                createCommunity();
                break;
            case 5:
                seeCommunities();
                break;
            case 6:
                joinCommunity();
                break;
            case 7:
                searchProfile();
                break;
            case 8:
                deleteProfile();
                break;
            case 9:
                return;
            default:
                System.out.println("Opção inválida!");
                break;

        }

    }

    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        int option;

        initialScreen();

        option = keyboard.nextInt();

        while(option!=3){
            switch (option){
                case 1:
                    createUser();
                    System.out.println("\n");
                    initialScreen();
                    break;
                case 2:
                    signIn();
                    initialScreen();
                    break;
                case 3:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!\nFavor, tente novamente");
                    break;
            }
            option = keyboard.nextInt();
        }

    }//Main
}
