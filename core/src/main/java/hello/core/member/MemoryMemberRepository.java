package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    /*
    * 동시성의 이슈로 인해 이러한 구조에서는
    * 단순 HashMap 보단 ConcurrentHashMap 을 쓴다고 하심
    * */

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
