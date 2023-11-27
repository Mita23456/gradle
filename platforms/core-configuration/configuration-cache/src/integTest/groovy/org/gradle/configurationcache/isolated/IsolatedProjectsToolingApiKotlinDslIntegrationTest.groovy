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

package org.gradle.configurationcache.isolated

import org.gradle.integtests.fixtures.build.KotlinDslTestProjectInitiation
import org.gradle.tooling.model.kotlin.dsl.KotlinDslScriptsModel

class IsolatedProjectsToolingApiKotlinDslIntegrationTest extends AbstractIsolatedProjectsToolingApiIntegrationTest implements KotlinDslTestProjectInitiation {

    def "can fetch KotlinDslScripts model for multi-project build with buildSrc"() {
        withMultiProjectBuildWithBuildSrc()

//        when: "fetching without Isolated Projects"
//        def expectedModel = fetchModel(KotlinDslScriptsModel)
//
//        then:
//        fixture.assertNoConfigurationCache()

        when: "fetching with Isolated Projects"
        executer.withArguments(ENABLE_CLI)
        def model = fetchModel(KotlinDslScriptsModel)

        then:
        model != null
//        fixture.assertStateStored {
//        }
//
//        when: "fetching again with Isolated Projects"
//        executer.withArguments(ENABLE_CLI)
//        fetchModel(KotlinDslScriptsModel)
//
//        then:
//        fixture.assertStateLoaded()
    }

}
