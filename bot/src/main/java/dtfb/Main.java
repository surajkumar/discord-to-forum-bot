package dtfb;

import dtfb.bot.BotStrategy;
import dtfb.bot.JdaBotStrategy;

public class Main {
    public static void main(String[] args) {
        BotStrategy strategy = new JdaBotStrategy("OTYyNjY4NzI0MTQ4NjMzNjQx.GH9rFu._zkXyBhWxHaD5qS70kJASeKILrKOZykt-QTibk");
        strategy.setup();
    }
}