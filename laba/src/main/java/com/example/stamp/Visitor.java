package com.example.stamp;

public interface Visitor {
    void visit(Task<?> task);
    void visit(Group<?> group);
    void visit(Signature<?> signature);
}
