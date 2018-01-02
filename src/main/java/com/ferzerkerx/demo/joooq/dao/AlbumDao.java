package com.ferzerkerx.demo.joooq.dao;

import com.ferzerkerx.demo.joooq.db.Tables;
import com.ferzerkerx.demo.joooq.db.tables.records.AlbumRecord;
import com.ferzerkerx.demo.joooq.model.Album;
import com.ferzerkerx.demo.joooq.model.Artist;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectWhereStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class AlbumDao {

    private final DSLContext dslContext;

    @Autowired
    public AlbumDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void deleteRecordsByArtistId(int artistId) {
        dslContext.deleteFrom(Tables.ALBUM)
                .where(Tables.ALBUM.ARTIST_ID.eq(artistId))
                .execute();
    }

    public List<Album> findRecordsByArtist(int artistId) {
        return dslContext.selectFrom(Tables.ALBUM)
                .where(Tables.ALBUM.ARTIST_ID.eq(artistId))
                .fetch()
                .stream()
                .map(AlbumDao::toAlbum)
                .collect(Collectors.toList());
    }

    private static Album toAlbum(AlbumRecord albumRecord) {
        Album album = new Album();
        album.setId(albumRecord.get(Tables.ALBUM.ALBUM_ID));
        album.setTitle(albumRecord.get(Tables.ALBUM.TITLE));
        album.setYear(albumRecord.get(Tables.ALBUM.YEAR));

        Artist artist = new Artist();
        artist.setId(albumRecord.get(Tables.ALBUM.ARTIST_ID));
        album.setArtist(artist);
        return album;
    }

    public void insert(Album album) {
        AlbumRecord albumRecord = dslContext.insertInto(Tables.ALBUM)
                .set(Tables.ALBUM.ARTIST_ID, album.getArtist().getId())
                .set(Tables.ALBUM.TITLE, album.getTitle())
                .set(Tables.ALBUM.YEAR, album.getYear())
                .returning(Tables.ALBUM.ALBUM_ID)
                .fetchOne();

        Integer insertedId = albumRecord.getValue(Tables.ALBUM.ALBUM_ID);
        album.setId(insertedId);
    }


    @Nullable
    public Album findById(int id) {
        return dslContext.selectFrom(Tables.ALBUM)
                .where(Tables.ALBUM.ALBUM_ID.eq(id))
                .fetchStream()
                .map(AlbumDao::toAlbum)
                .findFirst().orElse(null);

    }

    public List<Album> findByCriteria(Album example) {

        SelectWhereStep<AlbumRecord> albumRecords = dslContext.selectFrom(Tables.ALBUM);

        List<Condition> conditions = new LinkedList<>();
        if (StringUtils.isNotEmpty(example.getTitle())) {
            conditions.add(Tables.ALBUM.TITLE.eq(example.getTitle()));
        }

        if (StringUtils.isNotEmpty(example.getYear())) {
            conditions.add(Tables.ALBUM.YEAR.eq(example.getYear()));
        }

        if (!CollectionUtils.isEmpty(conditions)) {
            albumRecords.where(conditions);
        }

        return albumRecords
                .fetch()
                .stream()
                .map(AlbumDao::toAlbum)
                .collect(Collectors.toList());
    }

    public void deleteById(int id) {
        dslContext.deleteFrom(Tables.ALBUM)
                .where(Tables.ALBUM.ALBUM_ID.eq(id))
                .execute();

    }

    public void delete(Album album) {
        deleteById(album.getId());
    }

    public void update(Album album) {
        dslContext.update(Tables.ALBUM)
                .set(Tables.ALBUM.TITLE, album.getTitle())
                .set(Tables.ALBUM.YEAR, album.getYear())
                .where(Tables.ALBUM.ALBUM_ID.eq(album.getId()))
                .execute();
    }

    public void deleteAll() {
        dslContext.deleteFrom(Tables.ALBUM)
                .execute();
    }
}
