package frisky51.recipe.services;

import frisky51.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface IUnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUOMs();
}