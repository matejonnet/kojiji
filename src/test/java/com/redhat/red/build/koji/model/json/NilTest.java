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
package com.redhat.red.build.koji.model.json;

import org.commonjava.rwx.vocab.Nil;
import org.junit.Test;

import java.io.IOException;

import static org.commonjava.rwx.vocab.Nil.NIL_VALUE;
import static org.junit.Assert.assertEquals;

public class NilTest
        extends AbstractJsonTest
{
    @Test
    public void jsonRoundTrip()
            throws VerificationException, IOException
    {
        Object info = NIL_VALUE;

        String json = mapper.writeValueAsString( info );
        System.out.println( json );

        Nil out = mapper.readValue( json, Nil.class );

        assertEquals( out, info );
    }
}
