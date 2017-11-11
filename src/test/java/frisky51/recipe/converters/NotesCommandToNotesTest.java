package frisky51.recipe.converters;

import frisky51.recipe.commands.NotesCommand;
import frisky51.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NOTES = "notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        // Given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(NOTES);

        // When
        Notes notes = converter.convert(notesCommand);

        // Then
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}