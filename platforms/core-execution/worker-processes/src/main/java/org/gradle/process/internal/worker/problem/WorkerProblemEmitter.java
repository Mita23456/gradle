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

package org.gradle.process.internal.worker.problem;

import org.gradle.api.NonNullApi;
import org.gradle.api.problems.Problem;
import org.gradle.api.problems.ProblemSummary;
import org.gradle.api.problems.internal.DefaultProblem;
import org.gradle.api.problems.internal.ProblemEmitter;

import java.util.List;

/**
 * Worker-side implementation of {@link ProblemEmitter}.
 * <p>
 * This emitter will use the {@link WorkerProblemProtocol} to communicate problems to the daemon.
 */
@NonNullApi
public class WorkerProblemEmitter implements ProblemEmitter {
    private final WorkerProblemProtocol protocol;

    public WorkerProblemEmitter(WorkerProblemProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void emit(Problem problem) {
        protocol.reportProblem((DefaultProblem) problem);
    }

    @Override
    public void emit(List<ProblemSummary> summaries) {
        protocol.reportSummaries(summaries);
    }
}
