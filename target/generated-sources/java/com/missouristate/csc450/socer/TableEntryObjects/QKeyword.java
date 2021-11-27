package com.missouristate.csc450.socer.TableEntryObjects;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKeyword is a Querydsl query type for Keyword
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKeyword extends EntityPathBase<Keyword> {

    private static final long serialVersionUID = 1551691618L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKeyword keyword1 = new QKeyword("keyword1");

    public final StringPath fileName = createString("fileName");

    public final QFunction function;

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Integer> propertyId = createNumber("propertyId", Integer.class);

    public final StringPath score = createString("score");

    public QKeyword(String variable) {
        this(Keyword.class, forVariable(variable), INITS);
    }

    public QKeyword(Path<? extends Keyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QKeyword(PathMetadata metadata, PathInits inits) {
        this(Keyword.class, metadata, inits);
    }

    public QKeyword(Class<? extends Keyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.function = inits.isInitialized("function") ? new QFunction(forProperty("function")) : null;
    }

}

