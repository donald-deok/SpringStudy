package hello.core.singleton;

public class SingletonService {
    // TODO: Singleton으로 만들 수 있는 다양한 방법이 있다
    // 찾아보기

    // TODO : static이 어디에 위치하는지 알아보기
    // 1. static 영역에 객체를 딱 1개만 생성하기
    private static final SingletonService instance = new SingletonService();

    // 3. 생성자를 private로 만들어 외부에서의 호출 막기
    private SingletonService() {
    }

    // 2. 객체 인스턴스가 필요하면 이 public 메서드로만 접근하여, 항상 동일한 instance를 받게끔 만들기
    public static SingletonService getInstance() {
        return instance;
    }

    public static void main(String[] args) {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
