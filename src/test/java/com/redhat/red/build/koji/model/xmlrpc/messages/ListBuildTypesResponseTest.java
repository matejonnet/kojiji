/**
 * Copyright (C) 2015 Red Hat, Inc. (jcasey@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.red.build.koji.model.xmlrpc.messages;

import com.redhat.red.build.koji.model.xmlrpc.KojiBuildType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ListBuildTypesResponseTest
                extends AbstractKojiMessageTest
{
    @Test
    public void verifyVsCapturedHttp() throws Exception
    {
        ListBuildTypesResponse parsed =
                        parseCapturedMessage( ListBuildTypesResponse.class, "listBuildTypes-response.xml" );
        assertListBuildTypesResponse( parsed );
    }

    private void assertListBuildTypesResponse( ListBuildTypesResponse parsed )
    {
        ListBuildTypesResponse expected = getInstance();
        assertThat( parsed.getBuildTypes().size(), equalTo( 4 ) );
        assertThat( parsed.getBuildTypes().containsAll( expected.getBuildTypes() ), equalTo( true ) );
    }

    private ListBuildTypesResponse getInstance()
    {
        List<KojiBuildType> list = new ArrayList<>( 4 );
        KojiBuildType one = new KojiBuildType( 1, "rpm" );
        KojiBuildType two = new KojiBuildType( 2, "maven" );
        KojiBuildType three = new KojiBuildType( 3, "win" );
        KojiBuildType four = new KojiBuildType( 4, "image" );
        list.add( one );
        list.add( two );
        list.add( three );
        list.add( four );

        ListBuildTypesResponse response = new ListBuildTypesResponse();
        response.setBuildTypes( list );
        return response;
    }

    @Test
    public void roundTrip() throws Exception
    {
        ListBuildTypesResponse parsed = roundTrip( ListBuildTypesResponse.class, getInstance() );
        assertListBuildTypesResponse( parsed );
    }
}
