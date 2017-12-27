/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.simplemap.impl;

import com.hazelcast.internal.serialization.DataSerializerHook;
import com.hazelcast.internal.serialization.impl.FactoryIdHelper;
import com.hazelcast.map.impl.query.QueryOperation;
import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.simplemap.impl.operations.CompilePredicateOperation;
import com.hazelcast.simplemap.impl.operations.CompilePredicateOperationFactory;
import com.hazelcast.simplemap.impl.operations.InsertOperation;
import com.hazelcast.simplemap.impl.operations.QueryOperationFactory;
import com.hazelcast.simplemap.impl.operations.SizeOperation;
import com.hazelcast.simplemap.impl.operations.SizeOperationFactory;

import static com.hazelcast.internal.serialization.impl.FactoryIdHelper.SIMPLE_MAP_GENERATOR_DS_FACTORY;
import static com.hazelcast.internal.serialization.impl.FactoryIdHelper.SIMPLE_MAP_GENERATOR_DS_FACTORY_ID;

public final class SimpleMapDataSerializerHook implements DataSerializerHook {

    public static final int F_ID = FactoryIdHelper.getFactoryId(SIMPLE_MAP_GENERATOR_DS_FACTORY, SIMPLE_MAP_GENERATOR_DS_FACTORY_ID);

    public static final int INSERT = 0;
    public static final int COMPILE_PREDICATE = 1;
    public static final int COMPILE_PREDICATE_OPERATION_FACTORY = 2;
    public static final int QUERY =3;
    public static final int QUERY_OPERATION_FACTORY = 4;
    public static final int SIZE = 5;
    public static final int SIZE_OPERATION_FACTORY = 6;

    @Override
    public int getFactoryId() {
        return F_ID;
    }

    @Override
    public DataSerializableFactory createFactory() {
        return new DataSerializableFactory() {
            @Override
            public IdentifiedDataSerializable create(int typeId) {
                switch (typeId) {
                    case INSERT:
                        return new InsertOperation();
                    case COMPILE_PREDICATE:
                        return new CompilePredicateOperation();
                    case COMPILE_PREDICATE_OPERATION_FACTORY:
                        return new CompilePredicateOperationFactory();
                    case QUERY:
                        return new QueryOperation();
                    case QUERY_OPERATION_FACTORY:
                        return new QueryOperationFactory();
                    case SIZE:
                        return new SizeOperation();
                    case SIZE_OPERATION_FACTORY:
                        return new SizeOperationFactory();
                    default:
                        return null;
                }
            }
        };
    }
}