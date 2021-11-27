package com.missouristate.csc450.socer.TableEntryObjects;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFunction is a Querydsl query type for Function
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFunction extends EntityPathBase<Function> {

    private static final long serialVersionUID = 1715589503L;

    public static final QFunction function = new QFunction("function");

    public final StringPath fileName = createString("fileName");

    public final StringPath functionContents = createString("functionContents");

    public final NumberPath<Integer> functionId = createNumber("functionId", Integer.class);

    public final ListPath<Keyword, QKeyword> keywordList = this.<Keyword, QKeyword>createList("keywordList", Keyword.class, QKeyword.class, PathInits.DIRECT2);

    public QFunction(String variable) {
        super(Function.class, forVariable(variable));
    }

    public QFunction(Path<? extends Function> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFunction(PathMetadata metadata) {
        super(Function.class, metadata);
    }

}

