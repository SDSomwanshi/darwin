export class Receipe {
    recipeId: string;
    recipeName: string;
    recipeNature: string;
    ingredients: Ingredients[]
    instructions: Instructions[]
    createdDate: string;
    updatedDate: string;
}

export class Ingredients {
  name: string;
}

export class Instructions {
  instructionName: string;
}