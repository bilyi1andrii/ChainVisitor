import org.junit.jupiter.api.Test;

import com.example.stamp.Group;
import com.example.stamp.Signature;
import com.example.stamp.StampingVisitor;
import com.example.stamp.Task;

import org.junit.jupiter.api.Assertions;

import java.util.function.Consumer;

public class StampTests {

    private static final int TEST_INPUT_TEN = 10;
    private static final int TEST_INPUT_FIVE = 5;
    private static final int TEST_INPUT_TWO = 2;
    private static final int TEST_EXPECTED_FOUR = 4;

    @Test
    void testSignatureApplyAndFreeze() {
        Consumer<Integer> consumer = x
        -> Assertions.assertEquals(TEST_INPUT_TEN, x);
        Signature<Integer> signature = new Signature<>(consumer);

        signature.apply(TEST_INPUT_TEN);

        Assertions.assertNotNull(signature.getId());
    }

    @Test
    void testGroupApply() {
        Group<Integer> group = new Group<>();
        group.addTask(new Signature<>(x
        -> Assertions.assertEquals(TEST_INPUT_FIVE, x)))
             .addTask(new Signature<>(x
             -> Assertions.assertTrue(x > 0)));

        group.apply(TEST_INPUT_FIVE);

        Assertions.assertNotNull(group.getGroupUuid());
        for (Task<Integer> task : group.getTasks()) {
            Assertions.assertNotNull(task.getHeader("groupId"));
        }
    }

    @Test
    void testNestedGroupApply() {
        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(x
        -> Assertions.assertEquals(TEST_INPUT_TWO, x)))
                   .addTask(new Signature<>(x
                   -> Assertions.assertEquals(TEST_EXPECTED_FOUR, x * 2)));

        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup)
             .addTask(new Signature<>(x
             -> Assertions.assertTrue(x % 2 == 0)));

        group.apply(TEST_INPUT_TWO);

        Assertions.assertNotNull(group.getGroupUuid());
        Assertions.assertNotNull(nestedGroup.getGroupUuid());

        for (Task<Integer> task : nestedGroup.getTasks()) {
            Assertions.assertEquals(
                nestedGroup.getHeader("groupId"),
                task.getHeader("groupId")
            );
        }
    }

    @Test
    void testVisitorAssignsGroupId() {
        Group<Integer> group = new Group<>();
        group.addTask(new Signature<>(x -> { }));
        group.addTask(new Signature<>(x -> { }));

        StampingVisitor visitor = new StampingVisitor();
        group.accept(visitor);

        String groupId = group.getHeader("groupId");
        Assertions.assertNotNull(groupId);

        for (Task<Integer> task : group.getTasks()) {
            Assertions.assertEquals(groupId,
            task.getHeader("groupId"));
        }
    }
}
