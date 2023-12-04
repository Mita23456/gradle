/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.build.event.types;

import org.gradle.api.NonNullApi;
import org.gradle.tooling.internal.protocol.InternalProblemSummary;
import org.gradle.tooling.internal.protocol.InternalProblemSummaryDetails;

import java.io.Serializable;
import java.util.List;

@NonNullApi
public class DefaultProblemSummaryDetails implements InternalProblemSummaryDetails, Serializable {
    private final List<InternalProblemSummary> summaries;

    public DefaultProblemSummaryDetails(List<InternalProblemSummary> summaries) {
        this.summaries = summaries;
    }

    @Override
    public List<InternalProblemSummary> getSummaries() {
        return summaries;
    }
}
