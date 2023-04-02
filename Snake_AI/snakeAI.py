# Import modules
import pygame
import torch
from agent import Agent
from game import SnakeGameAI

# Define constants
FPS = 60
WINDOW_WIDTH = 480
WINDOW_HEIGHT = 480

# Load the trained model
model_path = "model/model.pth"
model = torch.load(model_path)

# Create an agent with the model
agent = Agent(model)

# Create a game instance
game = SnakeGameAI(WINDOW_WIDTH, WINDOW_HEIGHT)

# Initialize pygame
pygame.init()
clock = pygame.time.Clock()
screen = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption('Snake AI')

# Main loop
while True:
    # Check for events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            quit()

    # Update the game state
    game.update()

    # Render the game screen
    game.render(screen)
    pygame.display.flip()

    # Set the FPS
    clock.tick(FPS)