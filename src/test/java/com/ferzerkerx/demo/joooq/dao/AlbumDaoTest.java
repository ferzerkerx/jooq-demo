package com.ferzerkerx.demo.joooq.dao;

import com.ferzerkerx.demo.joooq.TestConfig;
import com.ferzerkerx.demo.joooq.model.Album;
import com.ferzerkerx.demo.joooq.model.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ferzerkerx.demo.joooq.Util.createAlbum;
import static com.ferzerkerx.demo.joooq.Util.createArtist;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class})
@Transactional(transactionManager="transactionManager")
@Rollback
public class AlbumDaoTest {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ArtistDao artistDao;

    private Artist artist;

    @Before
    public void setUp() {
        artist = createArtist();
        artistDao.insert(artist);
    }

    @After
    public void tearDown() {
        albumDao.deleteAll();
        artistDao.deleteAll();
    }

    @Test
    public void deleteRecordsByArtistId() {
        Album album = createAlbum(artist);
        albumDao.insert(album);

        List<Album> recordsByArtist = albumDao.findRecordsByArtist(artist.getId());
        assertNotNull(recordsByArtist);
        assertEquals(1, recordsByArtist.size());

        albumDao.deleteRecordsByArtistId(artist.getId());

        List<Album> emptyByArtist = albumDao.findRecordsByArtist(artist.getId());
        assertNotNull(emptyByArtist);
        assertTrue(emptyByArtist.isEmpty());
    }

    @Test
    public void deleteById() {
        Album album = createAlbum(artist);

        albumDao.insert(album);
        int id = album.getId();
        assertNotNull(albumDao.findById(id));

        albumDao.deleteById(id);
        assertNull(albumDao.findById(id));
    }

    @Test
    public void delete() {
        Album album = createAlbum(artist);

        albumDao.insert(album);
        int id = album.getId();
        assertNotNull(albumDao.findById(id));

        albumDao.delete(album);
        assertNull(albumDao.findById(id));
    }

    @Test
    public void update() {
        Album album = createAlbum(artist);
        albumDao.insert(album);

        Album fetchedAlbum = albumDao.findById(album.getId());
        assertNotNull(fetchedAlbum);
        assertEquals(album, fetchedAlbum);

        fetchedAlbum.setTitle("some title");
        fetchedAlbum.setYear("2004");

        albumDao.update(fetchedAlbum);

        Album updatedAlbum = albumDao.findById(album.getId());
        assertEquals(fetchedAlbum, updatedAlbum);

    }

    @Test
    public void insert() {
        Album album = createAlbum(artist);
        albumDao.insert(album);

        Album foundAlbum = albumDao.findById(album.getId());
        assertEquals(album, foundAlbum);
    }

    @Test
    public void findByCriteria() {
        Album album = createAlbum(artist);
        albumDao.insert(album);

        List<Album> byFullCriteria = albumDao.findByCriteria(album);
        assertNotNull(byFullCriteria);
        assertEquals(1, byFullCriteria.size());
        assertEquals(album, byFullCriteria.get(0));

        Album titleAlbum = new Album();
        titleAlbum.setTitle(album.getTitle());

        List<Album> byTitle = albumDao.findByCriteria(titleAlbum);
        assertNotNull(byTitle);
        assertEquals(1, byTitle.size());
        assertEquals(album, byTitle.get(0));


        Album yearAlbum = new Album();
        yearAlbum.setYear(album.getYear());

        List<Album> byYear = albumDao.findByCriteria(yearAlbum);
        assertNotNull(byYear);
        assertEquals(1, byYear.size());
        assertEquals(album, byYear.get(0));
    }

    @Test
    public void findRecordsByArtist() {
        List<Album> emptyRecordsByArtist = albumDao.findRecordsByArtist(artist.getId());
        assertNotNull(emptyRecordsByArtist);
        assertTrue(emptyRecordsByArtist.isEmpty());

        Album album = createAlbum(artist);
        albumDao.insert(album);


        List<Album> recordsByArtist = albumDao.findRecordsByArtist(artist.getId());
        assertNotNull(recordsByArtist);
        assertEquals(1, recordsByArtist.size());

        assertEquals(album, recordsByArtist.get(0));
    }
}