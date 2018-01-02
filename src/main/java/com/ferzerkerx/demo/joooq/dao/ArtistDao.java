package com.ferzerkerx.demo.joooq.dao;

import com.ferzerkerx.demo.joooq.db.Tables;
import com.ferzerkerx.demo.joooq.db.tables.records.ArtistRecord;
import com.ferzerkerx.demo.joooq.model.Artist;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ArtistDao {

    private final DSLContext dslContext;

    @Autowired
    public ArtistDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Artist> findAllArtists() {
        Stream<ArtistRecord> artistRecordStream = dslContext.selectFrom(Tables.ARTIST)
                .fetchStream();

        return toList(artistRecordStream);
    }

    public List<Artist> findMatchedArtistsByName(String name) {
        Stream<ArtistRecord> artistRecordStream = dslContext.selectFrom(Tables.ARTIST)
                .where(Tables.ARTIST.NAME.likeIgnoreCase(name))
                .fetchStream();
        return toList(artistRecordStream);
    }

    private List<Artist> toList(Stream<ArtistRecord> artistRecordStream) {
        return artistRecordStream
                .map(ArtistDao::toArtist)
                .collect(Collectors.toList());
    }

    private static Artist toArtist(ArtistRecord artistRecord) {
        Artist artist = new Artist();
        artist.setId(artistRecord.getArtistId());
        artist.setName(artistRecord.getName());
        return artist;
    }

    public void insert(Artist artist) {
        ArtistRecord artistRecord = dslContext.insertInto(Tables.ARTIST)
                .set(Tables.ARTIST.NAME, artist.getName())
                .returning(Tables.ARTIST.ARTIST_ID)
                .fetchOne();
        Integer insertedId = artistRecord.getValue(Tables.ARTIST.ARTIST_ID);
        artist.setId(insertedId);
    }

    @Nullable
    public Artist findById(int id) {
        return dslContext.selectFrom(Tables.ARTIST)
                .where(Tables.ARTIST.ARTIST_ID.eq(id))
                .fetchStream()
                .map(ArtistDao::toArtist)
                .findFirst().orElse(null);

    }

    public void deleteById(int id) {
        dslContext.deleteFrom(Tables.ARTIST)
                .where(Tables.ARTIST.ARTIST_ID.eq(id))
                .execute();

    }

    public void delete(Artist artist) {
        deleteById(artist.getId());
    }

    public void update(Artist artist) {
        dslContext.update(Tables.ARTIST)
                .set(Tables.ARTIST.NAME, artist.getName())
                .where(Tables.ARTIST.ARTIST_ID.eq(artist.getId()))
                .execute();
    }

    public void deleteAll() {
        dslContext.deleteFrom(Tables.ARTIST)
                .execute();
    }
}
