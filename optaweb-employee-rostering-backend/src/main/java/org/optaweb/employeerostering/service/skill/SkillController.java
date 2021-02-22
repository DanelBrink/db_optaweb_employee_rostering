/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaweb.employeerostering.service.skill;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.optaweb.employeerostering.domain.skill.Skill;
import org.optaweb.employeerostering.domain.skill.view.SkillView;

@Path("/rest/tenant/{tenantId}/skill")
@ApplicationScoped
// @Api(tags = "Skill")
public class SkillController {

    private final SkillService skillService;

    @Inject
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // @ApiOperation("Get a list of all skills")
    @GET
    @Path("/")
    public List<Skill> getSkillList(@PathParam("tenantId") @Min(0) Integer tenantId) {
        return skillService.getSkillList(tenantId);
    }

    // @ApiOperation("Get a skill by id")
    @GET
    @Path("/{id}")
    public Skill getSkill(@PathParam("tenantId") @Min(0) Integer tenantId,
            @PathParam("id") @Min(0) Long id) {
        return skillService.getSkill(tenantId, id);
    }

    // @ApiOperation("Delete a skill")
    @DELETE
    @Path("/{id}")
    public Boolean deleteSkill(@PathParam("tenantId") @Min(0) Integer tenantId,
            @PathParam("id") @Min(0) Long id) {
        return skillService.deleteSkill(tenantId, id);
    }

    // @ApiOperation("Add a new skill")
    @POST
    @Path("/add")
    public Skill createSkill(@PathParam("tenantId") @Min(0) Integer tenantId,
            @Valid SkillView skillView) {
        return skillService.createSkill(tenantId, skillView);
    }

    // @ApiOperation("Update a skill")
    @POST
    @Path("/update")
    public Skill updateSkill(@PathParam("tenantId") @Min(0) Integer tenantId,
            @Valid SkillView skillView) {
        return skillService.updateSkill(tenantId, skillView);
    }
}
