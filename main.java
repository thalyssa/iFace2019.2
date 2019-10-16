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

    //GERAL

    public static int getUserID(String login){
        for(int i=0;i<countUsers;i++){
            if(login.equals(userLogins[i]) && userIDs[i]!=-1) {
                return userIDs[i];
            }
        }//Fim for
        return -1;
    }


    public static void initialScreen(){
        System.out.println("\n\n\n");
        System.out.println("Bem vindo ao iFace!\nPor favor, escolha uma opção: ");
        System.out.println("1. Registrar-se");
        System.out.println("2. Login");
        System.out.println("3. Sair");
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
