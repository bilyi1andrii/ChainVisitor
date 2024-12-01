package com.example.stamp;

import java.util.UUID;

public class StampingVisitor implements Visitor {

    private final String groupId;

    public StampingVisitor() {
        this.groupId = UUID.randomUUID().toString();
    }

    @Override
    public void visit(Task<?> task) {
        task.setHeader("groupId", groupId);
    }

    @Override
    public void visit(Group<?> group) {
        group.setHeader("groupId", groupId);
        for (Task< ? > task : group.getTasks()) {
            task.accept(this);
        }
    }

    @Override
    public void visit(Signature<?> signature) {
        signature.setHeader("groupId", groupId);
    }

}
