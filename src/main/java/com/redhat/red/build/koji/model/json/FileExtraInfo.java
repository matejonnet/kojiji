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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;
import org.commonjava.rwx.anno.DataKey;
import org.commonjava.rwx.anno.StructPart;

import static com.redhat.red.build.koji.model.json.KojiJsonConstants.GAV_INFO;

/**
 * Created by jdcasey on 9/15/16.
 */
@StructPart
public class FileExtraInfo
{
    @JsonProperty( GAV_INFO )
    @DataKey( GAV_INFO )
    private GavExtraInfo gavExtraInfo;

    public FileExtraInfo( @JsonProperty( GAV_INFO ) GavExtraInfo gavExtraInfo)
    {
        this.gavExtraInfo = gavExtraInfo;
    }

    public FileExtraInfo()
    {
    }

    public void setGavExtraInfo( GavExtraInfo gavExtraInfo)
    {
        this.gavExtraInfo = gavExtraInfo;
    }

    public FileExtraInfo( ProjectVersionRef gav )
    {
        this.gavExtraInfo = new GavExtraInfo( gav );
    }

    public GavExtraInfo getGavExtraInfo()
    {
        return gavExtraInfo;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( !( o instanceof FileExtraInfo ) )
        {
            return false;
        }

        FileExtraInfo that = (FileExtraInfo) o;

        return getGavExtraInfo() != null ?
                getGavExtraInfo().equals( that.getGavExtraInfo() ) :
                that.getGavExtraInfo() == null;

    }

    @Override
    public int hashCode()
    {
        return getGavExtraInfo() != null ? getGavExtraInfo().hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "FileGavInfo{" +
                "gavInfo=" + gavExtraInfo +
                '}';
    }
}
