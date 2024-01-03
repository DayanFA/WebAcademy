public class Exemplo {
    public static boolean teste() {
        int a = 1;
        int b = 2;
        return a == b;
    }
    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.setMatricula(100);
        // aluno.setNome("Daniel");
        System.out.println(aluno.getNome());
    }
}