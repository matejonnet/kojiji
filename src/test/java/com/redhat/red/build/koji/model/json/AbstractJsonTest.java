package com.redhat.red.build.koji.model.json;

import com.redhat.red.build.koji.model.json.util.ExtraInfoHelper;
import com.redhat.red.build.koji.model.json.util.KojiObjectMapper;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;
import org.commonjava.maven.atlas.ident.ref.SimpleProjectVersionRef;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jdcasey on 2/16/16.
 */
public class AbstractJsonTest
{

    protected SimpleProjectVersionRef mainGav = new SimpleProjectVersionRef( "group.id", "artifact-id", "ver.sio.n" );

    protected KojiObjectMapper mapper = new KojiObjectMapper();

    protected Map<String, Object> newExtraInfo( ProjectVersionRef gav )
    {
        Map<String, Object> src = new HashMap<>();
        ExtraInfoHelper.addMavenInfo( gav, src );

        return src;
    }

    protected BuildSource newBuildSource()
    {
        return new BuildSource( "https://github.com/release-engineering/kojiji", "abcdefg" );
    }

    protected BuildDescription newBuildDescription()
            throws VerificationException
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Date( System.currentTimeMillis() - 30000 ) );
        cal.set( Calendar.MILLISECOND, 0 );

        BuildDescription.Builder builder =
                new BuildDescription.Builder( String.format( "%s-%s", mainGav.getGroupId(), mainGav.getArtifactId() ),
                                              mainGav.getVersionString() ).withStartTime(
                        cal.getTime() );

        cal.setTime( new Date() );
        cal.set( Calendar.MILLISECOND, 0 );

        BuildDescription src = builder.withEndTime( cal.getTime() )
                                                                          .withBuildType( "maven" )
                                                                          .withBuildSource( newBuildSource() )
                                                                          .build();

        src.setExtraInfo( newExtraInfo( mainGav ) );
        return src;
    }

    protected BuildHost newBuildHost()
    {
        return new BuildHost( "rhel-7", "x86_64" );
    }

    protected BuildTool newBuildTool( String name, String version )
    {
        return new BuildTool( name, version );
    }

    protected BuildContainer newBuildContainer( String type, String arch )
    {
        return new BuildContainer( type, arch );
    }

    protected BuildRoot newBuildRoot()
            throws VerificationException
    {
        BuildRoot root = new BuildRoot.Builder( 1 ).withContainer( newBuildContainer( "docker", "x86_64" ) )
                                                   .withHost( newBuildHost() )
                                                   .withTools( Arrays.asList( newBuildTool( "docker", "1.5.0" ),
                                                                              newBuildTool( "maven", "3.3.1" ) )
                                                                     .stream()
                                                                     .collect( Collectors.toSet() ) )
                                                   .withContentGenerator( "test-cg", "0.8" )
                                                   .withExtraInfo( "test-cg",
                                                                   Collections.singletonMap( "buildId", 1001 ) )
                                                   .build();

        return root;
    }

    protected BuildOutput newBuildOutput( int buildrootId, String filename )
            throws VerificationException
    {
        return new BuildOutput.Builder( buildrootId, filename ).withArch( StandardArchitecture.noarch )
                                                               .withChecksum( "md5", "aaffddcceeddaa" )
                                                               .withFileSize( 12345 )
                                                               .withMavenInfoAndType(
                                                                       new SimpleProjectVersionRef( "org.foo", "bar",
                                                                                                    "1.0" ) )
                                                               .build();
    }

    protected BuildOutput newLogOutput( int buildrootId, String filename )
            throws VerificationException
    {
        return new BuildOutput.Builder( buildrootId, filename ).withArch( StandardArchitecture.noarch )
                                                               .withChecksum( "md5", "aaffddcceeddaa" )
                                                               .withFileSize( 12345 )
                                                               .withOutputType( StandardOutputType.log )
                                                               .build();
    }
}
