/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Licensed under the Camunda License 1.0. You may not use this file
 * except in compliance with the Camunda License 1.0.
 */
package io.camunda.optimize.dto.optimize.query.report.combined;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.camunda.optimize.dto.optimize.query.report.ReportDefinitionUpdateDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CombinedProcessReportDefinitionUpdateDto extends ReportDefinitionUpdateDto {

  protected CombinedReportDataDto data;

  public CombinedProcessReportDefinitionUpdateDto() {}

  public CombinedReportDataDto getData() {
    return data;
  }

  public void setData(final CombinedReportDataDto data) {
    this.data = data;
  }

  @Override
  protected boolean canEqual(final Object other) {
    return other instanceof CombinedProcessReportDefinitionUpdateDto;
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(final Object o) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public String toString() {
    return "CombinedProcessReportDefinitionUpdateDto(data=" + getData() + ")";
  }
}
