package frisky51.recipe.converters;

import frisky51.recipe.commands.NotesCommand;
import frisky51.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NOTES = "notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        // Given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(NOTES);

        // When
        NotesCommand notesCommand = converter.convert(notes);

        // Then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(NOTES, notesCommand.getRecipeNotes());
    }
}