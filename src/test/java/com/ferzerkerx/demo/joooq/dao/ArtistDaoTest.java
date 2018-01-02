package com.ferzerkerx.demo.joooq.dao;

import com.ferzerkerx.demo.joooq.TestConfig;
import com.ferzerkerx.demo.joooq.model.Artist;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ferzerkerx.demo.joooq.Util.createArtist;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class})
@Transactional(transactionManager="transactionManager")
@Rollback
public class ArtistDaoTest {

    @Autowired
    private ArtistDao artistDao;

    @After
    public void tearDown() {
        artistDao.deleteAll();
    }

    @Test
    public void findMatchedArtistsByName() {
        Artist artist = createArtist();
        artistDao.insert(artist);


        List<Artist> matchedArtistsByName = artistDao.findMatchedArtistsByName(artist.getName());
        assertNotNull(matchedArtistsByName);
        assertEquals(1, matchedArtistsByName.size());
        assertEquals(artist, matchedArtistsByName.get(0));
    }

    @Test
    public void findAllArtists() {
        List<Artist> emptyArtists = artistDao.findAllArtists();
        assertNotNull(emptyArtists);
        assertTrue(emptyArtists.isEmpty());

        artistDao.insert(createArtist());
        artistDao.insert(createArtist());


        List<Artist> artists = artistDao.findAllArtists();
        assertNotNull(artists);
        assertEquals(2, artists.size());
    }


    @Test
    public void deleteById() {
        Artist artist = createArtist();
        artistDao.insert(artist);

        Artist storedArtist = artistDao.findById(artist.getId());
        assertNotNull(storedArtist);
        assertEquals(artist, storedArtist);

        artistDao.deleteById(storedArtist.getId());
        assertNull(artistDao.findById(storedArtist.getId()));
    }

    @Test
    public void delete() {
        Artist artist = createArtist();
        artistDao.insert(artist);

        Artist storedArtist = artistDao.findById(artist.getId());
        assertNotNull(storedArtist);
        assertEquals(artist, storedArtist);

        artistDao.delete(storedArtist);
        assertNull(artistDao.findById(storedArtist.getId()));
    }

    @Test
    public void update() {
        Artist artist = createArtist();
        artistDao.insert(artist);

        Artist storedArtist = artistDao.findById(artist.getId());
        assertNotNull(storedArtist);
        assertEquals(artist, storedArtist);

        storedArtist.setName("new name");
        artistDao.update(storedArtist);

        Artist updatedArtist = artistDao.findById(storedArtist.getId());
        assertEquals(storedArtist, updatedArtist);
    }

    @Test
    public void insert() {
        Artist artist = createArtist();
        artistDao.insert(artist);

        Artist storedArtist = artistDao.findById(artist.getId());
        assertEquals(artist, storedArtist);
    }
}