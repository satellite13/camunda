/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Licensed under the Camunda License 1.0. You may not use this file
 * except in compliance with the Camunda License 1.0.
 */
package io.camunda.optimize.service.db.schema.index.index;

import static io.camunda.optimize.service.db.DatabaseConstants.OPTIMIZE_DATE_FORMAT;
import static io.camunda.optimize.service.db.DatabaseConstants.POSITION_BASED_IMPORT_INDEX_NAME;

import co.elastic.clients.elasticsearch._types.mapping.DynamicMapping;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import io.camunda.optimize.dto.optimize.index.ImportIndexDto;
import io.camunda.optimize.dto.optimize.index.PositionBasedImportIndexDto;
import io.camunda.optimize.service.db.schema.DefaultIndexMappingCreator;

public abstract class PositionBasedImportIndex<TBuilder>
    extends DefaultIndexMappingCreator<TBuilder> {

  public static final int VERSION = 3;

  private static final String LAST_IMPORT_EXECUTION_TIMESTAMP =
      ImportIndexDto.Fields.lastImportExecutionTimestamp;
  private static final String POSITION_OF_LAST_ENTITY =
      PositionBasedImportIndexDto.Fields.positionOfLastEntity;
  private static final String SEQUENCE_OF_LAST_ENTITY =
      PositionBasedImportIndexDto.Fields.sequenceOfLastEntity;
  private static final String HAS_SEEN_SEQUENCE_FIELD =
      PositionBasedImportIndexDto.Fields.hasSeenSequenceField;
  private static final String TIMESTAMP_OF_LAST_ENTITY =
      ImportIndexDto.Fields.timestampOfLastEntity;
  private static final String DB_TYPE_INDEX_REFERS_TO =
      PositionBasedImportIndexDto.Fields.dbTypeIndexRefersTo;
  private static final String DATA_SOURCE = ImportIndexDto.Fields.dataSource;

  @Override
  public String getIndexName() {
    return POSITION_BASED_IMPORT_INDEX_NAME;
  }

  @Override
  public boolean isImportIndex() {
    return true;
  }

  @Override
  public int getVersion() {
    return VERSION;
  }

  @Override
  public TypeMapping.Builder addProperties(final TypeMapping.Builder builder) {

    return builder
        .properties(DATA_SOURCE, p -> p.object(o -> o.dynamic(DynamicMapping.True)))
        .properties(DB_TYPE_INDEX_REFERS_TO, p -> p.keyword(o -> o))
        .properties(POSITION_OF_LAST_ENTITY, p -> p.keyword(o -> o))
        .properties(SEQUENCE_OF_LAST_ENTITY, p -> p.keyword(o -> o))
        .properties(HAS_SEEN_SEQUENCE_FIELD, p -> p.boolean_(o -> o))
        .properties(TIMESTAMP_OF_LAST_ENTITY, p -> p.date(o -> o.format(OPTIMIZE_DATE_FORMAT)))
        .properties(
            LAST_IMPORT_EXECUTION_TIMESTAMP, p -> p.date(o -> o.format(OPTIMIZE_DATE_FORMAT)));
  }
}
