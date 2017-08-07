/*
 	Copyright (c) 2017 SuperiorCraft Team (Brian Zhou, Auguste Rame, Brandon Reed, and Jayden Johnson)

 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.

 */

package com.superiorcraft;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Banner;
import org.bukkit.block.BlockState;
import org.bukkit.block.NoteBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.superiorcraft.api.ClearGlass;
import com.superiorcraft.api.Elevator;
import com.superiorcraft.api.HealingPads;
import com.superiorcraft.api.MagicalWood;
import com.superiorcraft.api.Registry;
import com.superiorcraft.api.WoodPanel;
import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomPanel;
import com.superiorcraft.api.blocks.CustomPanelTexture;
import com.superiorcraft.api.crafting.CustomCrafting;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.items.food.CustomFood;
import com.superiorcraft.api.items.food.Salad;
import com.superiorcraft.api.map.CustomMap;
import com.superiorcraft.api.more.PolishedQuartz;
import com.superiorcraft.api.slabs.Slab;
import com.superiorcraft.api.more.PolishedGold;
import com.superiorcraft.api.util.CameraUtil;
import com.superiorcraft.api.util.DamageIndicator;
import com.superiorcraft.api.util.Hologram;
import com.superiorcraft.api.util.JarUtils;
import com.superiorcraft.api.util.Menu;
import com.superiorcraft.api.util.WebUtil;
import com.superiorcraft.api.util.item.ItemConstruct;
import com.superiorcraft.api.util.json.JsonReader;
import com.superiorcraft.api.util.json.MultilineMessage;
import com.superiorcraft.city.HoverBike;
import com.superiorcraft.city.Police;
import com.superiorcraft.commands.AddMin;
import com.superiorcraft.commands.CommandConstruct;
import com.superiorcraft.logicrace.RoomGenerator;
import com.superiorcraft.music.MusicPlayer;
import com.superiorcraft.nms.JsonMessage;
import com.superiorcraft.nms.NMSAdapter;
import com.superiorcraft.trollcraft.GhostBlock;

public class Main extends JavaPlugin implements Listener {

	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static Main plugin;
	private ArrayList<Player> cloaked = new ArrayList<Player>();
	static ArrayList<Player> inStealthMode = new ArrayList<Player>();

	public HashMap<Player, String> inBlockWars = new HashMap<Player, String>();
	public HashMap<Player, Integer> bwfct = new HashMap<Player, Integer>();

	public static Menu wm1;
	public static Menu wm2;
	public static Menu gselect;

	public static Menu gmode1s;

	@Override
	public void onEnable() {
		try {
            final File[] libs = new File[] {
                    new File(getDataFolder(), "jsoup.jar"),
                     };
            for (final File lib : libs) {
                if (!lib.exists()) {
                    JarUtils.extractFromJar(lib.getName(),
                            lib.getAbsolutePath());
                }
            }
            for (final File lib : libs) {
                if (!lib.exists()) {
                    getLogger().log(Level.SEVERE,
                            "There was a critical error loading SuperiorCraft! Could not find lib: "
                                    + lib.getName());
                    JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft] There was a critical error loading SuperiorCraft! Could not find lib: " + lib.getName(), "red", "If you are not a developer, you can ignore this", "light_purple", "")});
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                    return;
                }
                addClassPath(JarUtils.getJarUrl(lib));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
		
		plugin = this;
		//Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "[SuperiorCraft] Hello World");
		logger.setLevel(Level.ALL);
		logger.info("\n---\nStarting SuperiorCraft initialization\n---");

		wm1 = new Menu("Main Weapon Selection", 36);
		wm2 = new Menu("Side Weapon Selection", 36);
		gselect = new Menu("&8&lGame Selection".replace('&', '§'), 45);
		gmode1s = new Menu("&8Creative Selection".replace('&', '§'), 9);

		/* Lobby Selection */

		ItemStack lobsl = new ItemStack(Material.OBSIDIAN);
		ItemMeta lobslmeta = lobsl.getItemMeta();

		lobslmeta.setDisplayName("&5&lLobby".replace('&', '§'));

		lobsl.setItemMeta(lobslmeta);

		gselect.addItem(lobsl, 10);

		/* Creative Selection */

		ItemStack gmode1 = new ItemStack(Material.BRICK);
		ItemMeta gmode1meta = gmode1.getItemMeta();

		gmode1meta.setDisplayName("&f&lCreative".replace('&', '§'));

		gmode1.setItemMeta(gmode1meta);

		gselect.addItem(gmode1, 11);

		/* Troll Craft Selection */

		ItemStack trollsl = new ItemStack(Material.COMMAND);
		ItemMeta trollslmeta = gmode1.getItemMeta();

		trollslmeta.setDisplayName("&4&lTroll Craft".replace('&', '§'));

		trollsl.setItemMeta(trollslmeta);

		gselect.addItem(trollsl, 12);

		/* PixelMon */

		ItemStack pixelsl = new ItemStack(Material.SKULL_ITEM);
		ItemMeta pixelslmeta = gmode1.getItemMeta();

		pixelslmeta.setDisplayName("&4&lPixel&r&f&lmon".replace('&', '§'));

		pixelsl.setItemMeta(pixelslmeta);

		gselect.addItem(pixelsl, 13);

		/* Creative Server Selection */

		ItemStack gmode1g = new ItemStack(Material.WOOL, 1, DyeColor.YELLOW.getWoolData());
		ItemMeta gmode1gmeta = gmode1g.getItemMeta();

		gmode1gmeta.setDisplayName("&e&lYellow Server".replace('&', '§'));

		gmode1g.setItemMeta(gmode1gmeta);

		gmode1s.addItem(gmode1g, 1);

		//Register Weapons

		ItemStack wpl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta wpl1m = wpl1.getItemMeta();

		wpl1m.setDisplayName("&f&lAssaults".replace('&', '§'));

		wpl1.setItemMeta(wpl1m);
		wm1.inv.setItem(0, wpl1);

		ItemStack wpl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta wpl2m = wpl2.getItemMeta();

		wpl2m.setDisplayName("&f&lSnipers".replace('&', '§'));

		wpl2.setItemMeta(wpl2m);
		wm1.inv.setItem(9, wpl2);

		ItemStack wpl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta wpl3m = wpl3.getItemMeta();

		wpl3m.setDisplayName("&f&lShotguns".replace('&', '§'));

		wpl3.setItemMeta(wpl3m);
		wm1.inv.setItem(18, wpl3);

		ItemStack wpl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta wpl4m = wpl4.getItemMeta();

		wpl4m.setDisplayName("&f&lSpecials".replace('&', '§'));

		wpl4.setItemMeta(wpl4m);
		wm1.inv.setItem(27, wpl4);

		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.WOOD_SWORD, "\"Woody\"", Material.FIREWORK_CHARGE, "The \"Wood Pecker\"", "Sniper", 0, 4, true, 1, false, 1, "Arrow", new PotionEffect(PotionEffectType.POISON, 300, 0, true, false)), this);
		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.DIAMOND_SWORD, "Shoe Shiner", Material.FIREWORK_CHARGE, "Dirty with DOOM", "Sniper", 20, 2, true), this);

		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.WOOD_AXE, "AttackMe 1", Material.FIREWORK_CHARGE, "Actually please don't attack me...", "Assault", 2, 0, false), this);

		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.WOOD_SPADE, "The Pin cushion", Material.FIREWORK_CHARGE, "Made by Grandma", "Shotgun", 2, 0, false, 1, false, 3), this);

		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.WOOD_SWORD, "Sprocket", Material.FIREWORK_CHARGE, "Flaming Balls of fire", "Special", 20, 5, false, 1, false, 1, "Rocket"), this);
		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.WOOD_SWORD, "Snowboom", Material.FIREWORK_CHARGE, "Flaming Balls of ice", "Special", 5, 3, false, 1, false, 1, "Snowball"), this);
		
		getServer().getPluginManager().registerEvents(new LongRangeWeapon(Material.WOOD_SWORD, "6-Coup", Material.GHAST_TEAR, "A little old...", "Side", -3.2, 0, false, 1, false, 1), this);

		// Register MYML

		MYML myml = new MYML();

		getCommand("myml").setExecutor(myml);

		getServer().getPluginManager().registerEvents(myml, this);

		// Register Hover Bike

		HoverBike hbike = new HoverBike();

		getCommand("hbike").setExecutor(hbike);
		getServer().getPluginManager().registerEvents(hbike, this);

		// Register Police

		Police pol = new Police();

		getCommand("police").setExecutor(pol);

		getServer().getPluginManager().registerEvents(pol, this);

		// Register upAPI

		CustomBlock bload = new CustomBlock("BlockLoader", "BlockLoader");
		getCommand("getblock").setExecutor(bload);
		bload.load();

		Registry.registerBlock(new Elevator("Elevator", "superiorcraft:elevator"));
		Registry.registerBlock(new MagicalWood("Magical Wood", "superiorcraft:magical_wood"));
		Registry.registerBlock(new HealingPads("Healing Pad", "superiorcraft:healing_pad"));
		Registry.registerBlock(new ClearGlass("Clear Glass", "superiorcraft:clear_glass"));

		Registry.registerBlock(new RoomGenerator("Room Generator", "logicrace:room_generator"));
		
		// mORE
		
		Registry.registerBlock(new PolishedGold("Polished Gold", "more:gold_polished"));
		Registry.registerBlock(new PolishedQuartz("Polished Quartz", "more:quartz_polished"));
		
		// Slabs
		Registry.registerBlock(new Slab("Slab", "slab:slab"));
		
		Registry.registerPanel(new WoodPanel("Wooden Panel", "superiorcraft:wood_panel", new CustomPanelTexture(CustomPanelTexture.WOOD_PANEL)));
		Registry.registerPanel(new WoodPanel("Iron Panel", "superiorcraft:iron_panel", new CustomPanelTexture(CustomPanelTexture.IRON_PANEL)));
		
		// FoodE
		
		Registry.registerItem(new Salad(new ItemConstruct(Material.DIAMOND_SPADE).getMeta().setData((short) 1).setUnbreakable(true).setName("&2Salad").removeFlags().getItem(), "foode:salad"));
		
		CustomItem iload = new CustomItem(null, "ItemLoader");
		getCommand("getitem").setExecutor(iload);
		iload.load();

		CustomCrafting ccraft = new CustomCrafting("&6Custom Crafter".replace('&', '§'));
		getServer().getPluginManager().registerEvents(ccraft, this);
		ccraft.load();

		// Register Commands

		AddMin adm = new AddMin();
		getCommand("addmin").setExecutor(adm);

		// Register Holograms
		Hologram.registerHolograms();
		getServer().getPluginManager().registerEvents(new DamageIndicator(), this);

		// Register Main

		getServer().getPluginManager().registerEvents(this, this);
		Registry.registerListener(new CustomMap());
		/* Music Loop */ //BukkitTask task = new MusicPlayer.MusicThread().runTaskTimer(this, 0, 10);
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				/*try {
					JSONObject json = JsonReader.readJsonFromUrl("http://localhost:3000/api/ip/192.168.1.19");
					String did = (String) ((JSONArray) json.get("remotes")).get(0);
				
					JSONObject movement = JsonReader.readJsonFromUrl("http://localhost:3000/api/movement/" + did);
					getPlayer("SuperAuguste").teleport(getPlayer("SuperAuguste").getLocation().add(0, 0, -(((Long)((JSONObject)((JSONObject) movement.get("movement")).get("rotation")).get("gamma")).doubleValue() / 20) ));
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				for (World world : Bukkit.getWorlds()) {
					for (Entity entity : world.getEntities()) {
						if (entity instanceof Arrow) {
							Arrow arrow = (Arrow) entity;
							if (arrow.isOnGround()) {
								arrow.remove();
							}
						}
						/*if (entity) {
							BlockBreakEvent(Block theBlock, null);
						}*/
						//System.out.println(CustomBlock.blocks.get(0));
					}
				}

				for (Player p : Bukkit.getOnlinePlayers()) {
					//p.sendBlockChange(p.getLocation(), Material.TORCH, (byte) 0);
					if (p.getScoreboardTags().contains("viewingCamera")) {
						p.setGameMode(GameMode.SPECTATOR);
						Location l = p.getLocation();
						l.setYaw(l.getYaw() + 1);
						p.teleport(l);
					}
					for (int i = 0; i < 11; i++) {
						if (p.getLocation().add(0, -i, 0).getBlock() != null && p.getLocation().add(0, -i, 0).getBlock().getType() != null && p.getLocation().add(0, -i, 0).getBlock().getType() == Material.NETHER_WART_BLOCK) {
							p.openInventory(gselect.inv);
							p.sendMessage("Select a game");
							//p.teleport(p.getLocation().add(0, 0, -1));
							//p.
						}
					}
					/*if (p.i/hMode.add(p);
	    				}
	    				else if (!p.isSneaking() && inStealthMode.contains(p)) {
	    					p.sendMessage(ChatColor.GRAY + "You are no longer in stealth mode");
	    					p.removePotionEffect(PotionEffectType.INVISIBILITY);
	    					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	    					inStealthMode.remove(p);
	    				}
	    				else if (inStealthMode.contains(p)) {
	    					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0, true, false));
	    					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2, true, false));
	    				}*/
					if (p.hasPermission("superiorcraft.cloak")) {
						//System.out.println(getConfig().getString("players." + p.getName() + ".cloaked"));
						if (!cloaked.contains(p)) {
							//ActionBarAPI.sendActionBar(p, ChatColor.RED + "Cloak: Disabled");
						} else {
							//ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Cloak: Enabled");
						}
					}
					ActionBarAPI.sendActionBar(p, (p.getFoodLevel() > 16 ? ChatColor.GREEN : ChatColor.RED) + "Food Level: " + p.getFoodLevel() + " / " + ChatColor.GRAY + "Saturation Level: " + p.getSaturation());
					if (inBlockWars.containsKey(p)) {
						if (p.getLocation().getBlock().getType() != Material.STANDING_BANNER) {
							if (bwfct.containsKey(p)) {
								bwfct.remove(p);
							}
						}
						if (p.isSneaking() && p.getLocation().getBlock().getType() == Material.STANDING_BANNER) {
							BlockState bs = p.getLocation().getBlock().getState();
							Banner bm = (Banner) bs;
							if (!bwfct.containsKey(p) && bm.getBaseColor() != DyeColor.valueOf(inBlockWars.get(p).toUpperCase())) {
								bwfct.put(p, 0);
							}
							if (bwfct.containsKey(p)) {
								int v = bwfct.get(p);
								bwfct.remove(p);
								//System.out.println((v / 50) * 10);
								ArrayList<Integer> marks = new ArrayList<Integer>();
								marks.add(25);
								marks.add(50);
								marks.add(75);

								if (marks.contains(v)) {
									p.sendTitle(ChatColor.GRAY + String.valueOf(v) + "%", null, 3, 3, 3);
								}
								bwfct.put(p, v + 1);
							}
							if (bm.getBaseColor() == DyeColor.valueOf(inBlockWars.get(p).toUpperCase())) {
								//System.out.println(p.getBedSpawnLocation());
								p.setBedSpawnLocation(p.getLocation(), true);
							}
							if (bm.getBaseColor() != DyeColor.valueOf(inBlockWars.get(p).toUpperCase()) && bwfct.containsKey(p) && bwfct.get(p) > 100) {
								bwfct.remove(p);
								for (Entity e : p.getNearbyEntities(2, 2, 2)) {
									if (e.getName().equals("flag")) {
										for (String tag : e.getScoreboardTags()) {
											e.removeScoreboardTag(tag);
										}
										e.addScoreboardTag(inBlockWars.get(p));
									}
								}
								for (Player p2 : Bukkit.getOnlinePlayers()) {
									if (inBlockWars.containsKey(p2)) {
										p2.sendTitle(ChatColor.GREEN + "+1 Flag", "For the " + ChatColor.valueOf(inBlockWars.get(p).toUpperCase()) + inBlockWars.get(p).substring(0, 1).toUpperCase() + inBlockWars.get(p).substring(1).toLowerCase() + " Team " + ChatColor.RESET);
									}
								}
								int tf = 0;
								int ttf = 0;

								for (Entity e : p.getWorld().getEntities()) {
									if (e.getName().equals("flag")) {
										ttf++;
										BlockState bs1 = e.getLocation().getBlock().getState();
										Banner bm1 = (Banner) bs;
										if (bm1.getBaseColor() == DyeColor.valueOf(inBlockWars.get(p).toUpperCase())) {
											tf++;
										}
										ItemStack wool = new ItemStack(Material.WOOL, 1, bm.getBaseColor().getWoolData());

										ItemMeta woolm = wool.getItemMeta();

										//player.sendMessage(String.valueOf(e.getLocation().getX()));
										//woolm.getLore().add("A");
										ArrayList<String> wlore = new ArrayList<String>();
										wlore.add(String.valueOf(e.getLocation().getX()));
										wlore.add(String.valueOf(e.getLocation().getY()));
										wlore.add(String.valueOf(e.getLocation().getZ()));

										woolm.setLore(wlore);
										wool.setItemMeta(woolm);

										//m.inv.addItem(wool);
									}
								}
								for (Player p2 : Bukkit.getOnlinePlayers()) {
									if (inBlockWars.containsKey(p2)) {
										if (tf == ttf) {
											p2.sendTitle(ChatColor.valueOf(inBlockWars.get(p).toUpperCase()) + inBlockWars.get(p).substring(0, 1).toUpperCase() + inBlockWars.get(p).substring(1).toLowerCase() + ChatColor.GREEN + "Won!", null);
										}
									}
								}
								bm.setBaseColor(DyeColor.valueOf(inBlockWars.get(p).toUpperCase()));
								bs.update();
							}

							/*Class<?> pclass = NMS.getClass("PacketPlayOutTitle");
	    						Constructor<?> pcon = pclass.getConstructor(NMS.getClass("PacketPlayOutTitle.EnumTitleAction"))
	    						Object packet = pclass.newInstance(NMS.getClass("PacketPlayOutTitle.EnumTitleAction").);
	    						System.out.println(NMS.getConnection(p));*/


						}
					}
				}
			}
		}, 0, 0);

		if (!(new File(getDataFolder(), "config.yml")).exists()) {
			saveDefaultConfig();
		}
		

		//System.out.println(MusicPlayer.translateMusicFileToNotes("test"));

		/*ItemStack pcrys = new ItemStack(Material.DIAMOND);
	    	ItemMeta pcrysm = pcrys.getItemMeta();
	    	pcrysm.setDisplayName("&b&lPower Crystal".replace('&', '§'));
	    	ArrayList<String> pcrysl = new ArrayList<String>();
	    	pcrysl.add("&b&l0 / 100,000 RF".replace('&', '§'));
	       	pcrysm.setLore(pcrysl);
	    	pcrys.setItemMeta(pcrysm);*/
		
		//JsonMessage.broadcastJsonMessage("{\"text\":\"[SuperiorCraft] Hello World\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/say hello\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"test\",\"color\":\"light_purple\"}]}}}");
		JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft] Started using " + NMSAdapter.getVersion(), "green", "If you are not a developer, you can ignore this", "light_purple", "")});
		logger.info("\n---\nFinished SuperiorCraft initialization\n---");
		getServer().createWorld(new WorldCreator("world"));
		
	}
	
	private void addClassPath(final URL url) throws IOException {
        final URLClassLoader sysloader = (URLClassLoader) ClassLoader
                .getSystemClassLoader();
        final Class<URLClassLoader> sysclass = URLClassLoader.class;
        try {
            final Method method = sysclass.getDeclaredMethod("addURL",
                    new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { url });
        } catch (final Throwable t) {
            t.printStackTrace();
            throw new IOException("Error adding " + url
                    + " to system classloader");
        }
    }

	@Override
	public void onDisable() {
		JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft] SuperiorCraft is being turned off", "red", "If you are not a developer, you can ignore this", "light_purple", "")});
	}

	@EventHandler
	public void onServerListPing(ServerListPingEvent e) {
		e.setMotd("§6§lSuperior§r§c§lCraft   §r§9[ §l" + Bukkit.getVersion().split("MC: ")[1].replace(")", "") + "§r§9 ]§r\n§4§lBlockWarz, §r§3§lmore to come");
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase(wm1.inv.getName()) && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE && wm1.inv.getItem(e.getSlot()) != null && wm1.inv.getItem(e.getSlot()).isSimilar(e.getCurrentItem())) {
			e.getWhoClicked().getInventory().setItem(0, e.getCurrentItem());
			e.getWhoClicked().getInventory().setItem(7, new ItemStack(Material.FIREWORK_CHARGE, 64));
			wm1.inv.setItem(e.getSlot(), e.getCurrentItem());
			//e.getWhoClicked().closeInventory();
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(wm2.inv);
			//e.getWhoClicked().setGameMode(GameMode.ADVENTURE);
		}

		else if (e.getInventory().getName().equalsIgnoreCase(wm2.inv.getName()) && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE && wm2.inv.getItem(e.getSlot()) != null && wm2.inv.getItem(e.getSlot()).isSimilar(e.getCurrentItem())) {
			e.getWhoClicked().getInventory().setItem(1, e.getCurrentItem());
			e.getWhoClicked().getInventory().setItem(8, new ItemStack(Material.GHAST_TEAR, 64));
			wm2.inv.setItem(e.getSlot(), e.getCurrentItem());
			e.getWhoClicked().closeInventory();
			//e.getWhoClicked().setGameMode(GameMode.ADVENTURE);
		}
		/*else if ((e.getInventory().getName().equalsIgnoreCase(wm1.inv.getName()) || e.getInventory().getName().equalsIgnoreCase(wm2.inv.getName())) && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
		}*/
		else if ((e.getInventory().getName().equalsIgnoreCase(wm1.inv.getName()) || e.getInventory().getName().equalsIgnoreCase(wm2.inv.getName()))) {
			e.setCancelled(true);
		}
		else if (e.getInventory().getName().equalsIgnoreCase("Flags")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getData().getData() == DyeColor.valueOf(inBlockWars.get(e.getWhoClicked()).toUpperCase()).getWoolData()) {
				//e.getWhoClicked().sendMessage(e.getCurrentItem().getItemMeta().getLore().get(0));
				Location l = new Location(e.getWhoClicked().getWorld(), Double.valueOf(e.getCurrentItem().getItemMeta().getLore().get(0)), Double.valueOf(e.getCurrentItem().getItemMeta().getLore().get(1)), Double.valueOf(e.getCurrentItem().getItemMeta().getLore().get(2)));

				e.getWhoClicked().teleport(l);
			}
		}
		else if (e.getInventory().getName().equalsIgnoreCase(gselect.inv.getName())) {
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();

				return;
			}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Creative")) {
				//e.getWhoClicked().sendMessage(ChatColor.YELLOW + "WIP");

				e.getWhoClicked().closeInventory();
				e.getWhoClicked().openInventory(gmode1s.inv);
			}

			else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby")) {
				e.getWhoClicked().closeInventory();

				Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "warp gamelobby");
			}

			else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Troll Craft")) {
				e.getWhoClicked().closeInventory();

				Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "warp trollcraft");
			}

			else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Pixel")) {
				e.getWhoClicked().closeInventory();

				Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "warp pixelmon");
			}

			gselect.inv.setItem(e.getSlot(), e.getCurrentItem());
		}
		else if (e.getInventory().getName().equals(e.getWhoClicked().getInventory().getName()) && !e.getWhoClicked().isOp()) {
			e.setCancelled(true);
			//e.setCursor(new ItemStack(Material.AIR));
			e.getWhoClicked().closeInventory();
		}
	}

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			if (e.getInventory().getName().equalsIgnoreCase(wm1.inv.getName())) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					@Override
					public void run() {
						e.getPlayer().openInventory(wm2.inv);
					}
				}, 1);

			}

			else if (e.getInventory().getName().equalsIgnoreCase(wm2.inv.getName())) {
				if (e.getInventory().getName().equalsIgnoreCase(wm2.inv.getName())) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						@Override
						public void run() {
							e.getPlayer().setGameMode(GameMode.ADVENTURE);
						}
					}, 1);

				}		
			}
		}
	}

	@EventHandler
	public void drop(PlayerDropItemEvent e) {
		if (!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.COMPASS &&  e.getItem().getItemMeta().getDisplayName() != null && e.getItem().getItemMeta().getDisplayName().contains("Weapons Selector")) {
			wm1.show(e.getPlayer());
		}

		else if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.COMPASS &&  e.getItem().getItemMeta().getDisplayName() != null && e.getItem().getItemMeta().getDisplayName().contains("Game Selector")) {
			gselect.show(e.getPlayer());
		}
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().isSneaking() && e.getClickedBlock().getType() != null && e.getClickedBlock().getType() == Material.NOTE_BLOCK) {
			((NoteBlock) e.getClickedBlock().getState()).play(Instrument.GUITAR, Note.natural(1, Tone.A));
			((NoteBlock) e.getClickedBlock().getState()).play(Instrument.GUITAR, Note.natural(1, Tone.C));
			
			JsonMessage.sendJsonMessages(e.getPlayer(), new JsonMessage[] {new JsonMessage("[ ", "green"), new JsonMessage(" ]", "green")});
			
			e.setCancelled(true);
		}

		//if (e.get)
		/*if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.IRON_SPADE) {
    		if (e.getPlayer().isOp()) {
    			if (e.getPlayer().getInventory().contains(Material.FIREWORK_CHARGE)) {
    				Fireball f = e.getPlayer().launchProjectile(Fireball.class);
    				f.setIsIncendiary(false);
    				f.setYield(0);
    				f.setSilent(true);
    				e.getPlayer().getInventory().removeItem(new ItemStack[] {new ItemStack(Material.FIREWORK_CHARGE, 1) });
    				if (e.getPlayer().getInventory().contains(Material.FIREWORK_CHARGE)) {
    					int x = 0;
    					do {`
    						x += 1;
    					} while (e.getPlayer().getInventory().contains(Material.FIREWORK_CHARGE, x));
    					x -= 1;
    					e.getPlayer().sendMessage(ChatColor.GRAY + "You have " + Integer.toString(x) + " ammo left");
    					ItemMeta meta = e.getItem().getItemMeta();
    					meta.setDisplayName(ChatColor.BOLD + "Super Shovel - " + Integer.toString(x) + " ammo");
    					e.getItem().setItemMeta(meta);
    				}
    			}
    			else {
    				e.getPlayer().sendMessage(ChatColor.RED + "You are out of ammo");
    				ItemMeta meta = e.getItem().getItemMeta();
					meta.setDisplayName(ChatColor.BOLD + "Super Shovel - 0 ammo");
					e.getItem().setItemMeta(meta);
    			}
    		}
    	}

    	else if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.BLAZE_ROD) {
    		if (e.getPlayer().isOp()) {
    			if (e.getPlayer().getInventory().contains(Material.BLAZE_POWDER)) {
    				Fireball f = e.getPlayer().launchProjectile(SmallFireball.class);
    				f.setIsIncendiary(false);
    				f.setYield(0);
    				f.setSilent(true);
    				e.getPlayer().getInventory().removeItem(new ItemStack[] {new ItemStack(Material.BLAZE_POWDER, 1) });
    				if (e.getPlayer().getInventory().contains(Material.BLAZE_POWDER)) {
    					int x = 0;
    					do {
    						x += 1;
    					} while (e.getPlayer().getInventory().contains(Material.BLAZE_POWDER, x));
    					x -= 1;
    					e.getPlayer().sendMessage(ChatColor.GRAY + "You have " + Integer.toString(x) + " ammo left");
    					ItemMeta meta = e.getItem().getItemMeta();
    					meta.setDisplayName(ChatColor.BOLD + "Super Rod - " + Integer.toString(x) + " ammo");
    					e.getItem().setItemMeta(meta);
    				}
    			}
    			else {
    				e.getPlayer().sendMessage(ChatColor.RED + "You are out of ammo");
    				ItemMeta meta = e.getItem().getItemMeta();
					meta.setDisplayName(ChatColor.BOLD + "Super Rod - 0 ammo");
					e.getItem().setItemMeta(meta);
    			}
    		}
    	}
		 */
		if (e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().isSneaking()) {
			if (e.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			}
			if (e.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
				e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
				//e.getPlayer().teleport(e.getPlayer().getLocation().add(0, 1, 0));
			}
			else {
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 255, true, false));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 235, true, false));
				//e.getPlayer().teleport(e.getPlayer().getLocation().add(0, -0.1, 0));
			}
		}
		/*
    	if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.WOOD_HOE && e.getItem().getItemMeta().getDisplayName().contains("Farmer Scope") && !cooldown.containsKey(e.getPlayer())) {
    		Material ammo = Material.FIREWORK_CHARGE;
    		if (e.getPlayer().getInventory().contains(ammo)) {
    			Arrow p = e.getPlayer().launchProjectile(Arrow.class);
    			p.setCritical(true);
    			p.setKnockbackStrength(0);
    			p.setSilent(false);
    			p.setCustomName("S1D");
    			e.getPlayer().getInventory().removeItem(new ItemStack[] {new ItemStack(ammo, 1) });
    			if (e.getPlayer().getInventory().contains(ammo)) {
    				int x = 0;
    				do {
    					x += 1;
    				} while (e.getPlayer().getInventory().contains(ammo, x));
    				x -= 1;
    				//e.getPlayer().sendMessage(ChatColor.GRAY + "You have " + Integer.toString(x) + " ammo left");
    				ItemMeta meta = e.getItem().getItemMeta();
    				meta.setDisplayName(ChatColor.BOLD + "Farmer Scope");
    				ArrayList<String> lore = new ArrayList<String>();
    				lore.add(ChatColor.BOLD + "A farmers trusty farming tool...");
    				lore.add(ChatColor.WHITE + "Sniper");
    				meta.setLore(lore);
    				//meta.setDisplayName(ChatColor.BOLD + "Farmer Scope - " + Integer.toString(x) + " ammo");
    				e.getItem().setItemMeta(meta);
    				cooldown.put(e.getPlayer(), 1);
    				Bukkit.getScheduler().runTaskLater(this, new Runnable() {
    					@Override
    					public void run() {
    						cooldown.remove(e.getPlayer());

    					}
    				}, 2 * 20);
    			}

    			else {
    				e.getPlayer().sendMessage(ChatColor.RED + "You are out of ammo");
    				//ItemMeta meta = e.getItem().getItemMeta();
					//meta.setDisplayName(ChatColor.BOLD + "Farmer Scope - No Ammo");
					//e.getItem().setItemMeta(meta);
    			}
    		}
    	}

    	if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.DIAMOND_HOE && e.getItem().getItemMeta().getDisplayName().contains("Shoe Shiner") && !cooldown.containsKey(e.getPlayer())) {
    		Material ammo = Material.FIREWORK_CHARGE;
    		if (e.getPlayer().getInventory().contains(ammo)) {
    			Arrow p = e.getPlayer().launchProjectile(Arrow.class);
    			p.setCritical(true);
    			p.setKnockbackStrength(0);
    			p.setSilent(false);
    			p.setCustomName("S10D");
    			e.getPlayer().getInventory().removeItem(new ItemStack[] {new ItemStack(ammo, 1) });
    			if (e.getPlayer().getInventory().contains(ammo)) {
    				int x = 0;
    				do {
    					x += 1;
    				} while (e.getPlayer().getInventory().contains(ammo, x));
    				x -= 1;
    				//e.getPlayer().sendMessage(ChatColor.GRAY + "You have " + Integer.toString(x) + " ammo left");
    				ItemMeta meta = e.getItem().getItemMeta();
    				meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner");
    				ArrayList<String> lore = new ArrayList<String>();
    				lore.add(ChatColor.BOLD + "Dirty with DOOM");
    				lore.add(ChatColor.WHITE + "Sniper");
    				meta.setLore(lore);
    				//meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner - " + Integer.toString(x) + " ammo");
    				e.getItem().setItemMeta(meta);
    				cooldown.put(e.getPlayer(), 1);
    				Bukkit.getScheduler().runTaskLater(this, new Runnable() {
    					@Override
    					public void run() {
    						cooldown.remove(e.getPlayer());

    					}
    				}, 1 * 20);
    			}

    			else {
    				e.getPlayer().sendMessage(ChatColor.RED + "You are out of ammo");
    				//ItemMeta meta = e.getItem().getItemMeta();
					//meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner - No Ammo");
					//e.getItem().setItemMeta(meta);
    			}
    		}
    	}

    	if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.WOOD_AXE && e.getItem().getItemMeta().getDisplayName().contains("AttackMe 1") && !cooldown.containsKey(e.getPlayer())) {
    		Material ammo = Material.FIREWORK_CHARGE;
    		if (e.getPlayer().getInventory().contains(ammo)) {
    			Arrow p = e.getPlayer().launchProjectile(Arrow.class);
    			//p.setCritical(true);
    			p.setBounce(false);
    			p.setKnockbackStrength(0);
    			p.setSilent(false);
    			p.setCustomName("A1D");
    			e.getPlayer().getInventory().removeItem(new ItemStack[] {new ItemStack(ammo, 1) });
    			if (e.getPlayer().getInventory().contains(ammo)) {
    				int x = 0;
    				do {
    					x += 1;
    				} while (e.getPlayer().getInventory().contains(ammo, x));
    				x -= 1;
    				//e.getPlayer().sendMessage(ChatColor.GRAY + "You have " + Integer.toString(x) + " ammo left");
    				ItemMeta meta = e.getItem().getItemMeta();
    				meta.setDisplayName(ChatColor.BOLD + "AttackMe 1");
    				ArrayList<String> lore = new ArrayList<String>();
    				lore.add(ChatColor.BOLD + "Actually please don't attack me...");
    				lore.add(ChatColor.WHITE + "Assault");
    				meta.setLore(lore);
    				//meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner - " + Integer.toString(x) + " ammo");
    				e.getItem().setItemMeta(meta);
    				cooldown.put(e.getPlayer(), 1);
    				Bukkit.getScheduler().runTaskLater(this, new Runnable() {
    					@Override
    					public void run() {
    						cooldown.remove(e.getPlayer());

    					}
    				}, 1);
    			}

    			else {
    				e.getPlayer().sendMessage(ChatColor.RED + "You are out of ammo");
    				//ItemMeta meta = e.getItem().getItemMeta();
					//meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner - No Ammo");
					//e.getItem().setItemMeta(meta);
    			}
    		}
    	}*/

		if (e.getPlayer().isGliding() && e.getAction() == Action.LEFT_CLICK_AIR) {
			e.getPlayer().setVelocity(e.getPlayer().getVelocity().multiply(2.0));
			e.getPlayer().sendMessage(ChatColor.GRAY + "Whoosh!");

		}
	}

	@EventHandler
	public void onEntityByEntityDamage(EntityDamageByEntityEvent e) {
		/*if ((e.getDamager() instanceof Fireball) && (((Fireball) e.getDamager()).getShooter() instanceof Player)) {
    		e.setDamage(20.0);
    	}

    	if ((e.getDamager() instanceof SmallFireball) && (((SmallFireball) e.getDamager()).getShooter() instanceof Player)) {
    		e.setDamage(100.0);
    	}*/
		/*if ((e.getDamager() instanceof Arrow) && (((Arrow) e.getDamager()).getShooter() instanceof Player) && e.getDamager().getCustomName() == "S1D") {
    		e.setDamage(e.getDamage() + 4);
    	}
    	if ((e.getDamager() instanceof Arrow) && (((Arrow) e.getDamager()).getShooter() instanceof Player) && e.getDamager().getCustomName() == "S10D") {
    		e.setDamage(e.getDamage() + 20);
    	}
    	if ((e.getDamager() instanceof Arrow) && (((Arrow) e.getDamager()).getShooter() instanceof Player) && e.getDamager().getCustomName() == "A1D") {
    		e.setDamage(e.getDamage() + 2);
    	}*/


	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e) {
		
	}/*
			if (player.getHealth() <= e.getDamage() || player.getHealth() <= 0 || player.getHealth() - e.getDamage() < 1) {
				//player.setHealth(20);
				//player.setFoodLevel(20);
				e.setCancelled(true);
				if (inBlockWars.containsKey(player)) {
					if (bwfct.containsKey(player)) {
						bwfct.remove(player);
					}
					getServer().broadcastMessage(ChatColor.RED + player.getName() + " has died.");

					player.closeInventory();
					player.getOpenInventory().close();
					player.getInventory().remove(new ItemStack(Material.FIREWORK_CHARGE));
					player.getInventory().remove(new ItemStack(Material.GHAST_TEAR));

					player.getInventory().setItem(7, new ItemStack(Material.FIREWORK_CHARGE, 64));
					player.getInventory().setItem(8, new ItemStack(Material.GHAST_TEAR, 64));

					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(ChatColor.GRAY + "You will respawn in 10 seconds...");

					Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
						@Override
						public void run() {
							player.teleport(player.getBedSpawnLocation());

							wm1.show(player);

						}
					}, 10 * 20);
					
					player.setHealth(20);
					player.setFoodLevel(20);
				}
				else {
					//player.teleport(player.getBedSpawnLocation());
				}
			}
		}
	}*/

	@EventHandler
	public void onEntityDeathEvent (EntityDeathEvent e) {
		//e.getDrops().clear();
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPermission("superiorcraft.editworld")) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You may not place blocks here");
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPermission("superiorcraft.editworld")) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You may not break blocks here");
		}
	}


	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		/*if (e.getPlayer().getTargetBlock((HashSet<Byte>) null, 100).getType() != Material.AIR) {
    		e.getPlayer().teleport(e.getPlayer().getLocation().add(0, 1, 0));
    	}*/

		/*for (Entity en : e.getPlayer().getWorld().getEntities()) {
    		for (int i = 0; i < en.getScoreboardTags().toArray().length; i++) {
    			String tag = en.getScoreboardTags().toString().replace('[', ' ').replace(']', ' ').trim().split(",")[i];
    			//System.out.println(tag);
    			if (tag.startsWith("p:")) {
    				//System.out.println(tag.substring(2, tag.length()));
    				if (tag.substring(2, tag.length()).equalsIgnoreCase(e.getPlayer().getName())) {
    					en.teleport(e.getPlayer().getLocation().add(0, -0.2, 0));
    					e.setFrom(en.getLocation());
    				}
    			}
    		}
    	}*/
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void onChat(AsyncPlayerChatEvent event) {
		if (getConfig().get("players." + event.getPlayer().getName() + ".muted") == "yes") {
			event.setCancelled(true);
			return;
		}
		if (!event.getPlayer().isOp()) {
			event.setFormat(ChatColor.LIGHT_PURPLE + "%s" + ChatColor.DARK_AQUA + " > " + ChatColor.GREEN + "%s");
		}
		else if (event.getPlayer().isOp()) {
			//System.out.println();
			
			for (String url : WebUtil.extractUrls(event.getMessage())) {
				//System.out.println(arg0);
				event.setMessage(event.getMessage().replace(url, WebUtil.getPageTitle(url) + " [ " + url + " ]"));
			}
			
			event.setFormat(ChatColor.LIGHT_PURPLE + getConfig().getString("players." + event.getPlayer().getName() + ".name") + ChatColor.DARK_AQUA + getConfig().getString("players." + event.getPlayer().getName() + ".suffix") + "> " + ChatColor.GREEN + event.getMessage());
		
			//event.setCancelled(true);
			//return;
		}
		if (!event.isCancelled()) {
			for (Player pl : event.getRecipients()) {
				//pl.play
				pl.playEffect(pl.getLocation(), Effect.CLICK2, 50);
			}
		}
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		/*if (player.getName().equals("smartzx")) {
    		e.disallow(e.getResult(), "Error 234567");
    	}*/
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		//player.kickPlayer("Get out of here!");

		if (!getConfig().getString("players." + player.getName() + ".cloaked").equals("yes")) {
			if (!player.isOp()) {
				e.setJoinMessage(ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.GREEN + " has joined the game");
			}
			else if (player.isOp()) {
				e.setJoinMessage(ChatColor.LIGHT_PURPLE + getConfig().getString("players." + e.getPlayer().getName() + ".name") + ChatColor.DARK_AQUA + getConfig().getString("players." + e.getPlayer().getName() + ".suffix") + ChatColor.GREEN + "joined the game");
			}
		}
		else {
			e.setJoinMessage(null);
		}
		for (Player ps : cloaked) {
			player.hidePlayer(ps);
		}
		if (getConfig().getString("players." + player.getName() + ".cloaked").equals("yes")) {
			cloaked.add(player);
		}

		ItemStack gs = new ItemStack(Material.COMPASS, 1);
		ItemMeta gsm = gs.getItemMeta();
		gsm.setDisplayName("&f&lGame Selector".replace('&', '§'));

		gs.setItemMeta(gsm);

		e.getPlayer().getInventory().setItem(8, gs);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();

		if (!getConfig().getString("players." + player.getName() + ".cloaked").equals("yes")) {
			if (!player.isOp()) {
				e.setQuitMessage(ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.GREEN + " has left the game");
			}
			else if (player.isOp()) {
				e.setQuitMessage(ChatColor.LIGHT_PURPLE + getConfig().getString("players." + e.getPlayer().getName() + ".name") + ChatColor.DARK_AQUA + getConfig().getString("players." + e.getPlayer().getName() + ".suffix") + ChatColor.GREEN + "left the game");
			}
		}
		else {
			e.setQuitMessage(null);
		}
		for (Player ps : cloaked) {
			player.hidePlayer(ps);
		}
	}

	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		//System.out.println("a");
	}

	public void nameTag(String name, String pname) {
		Player player = getPlayer(pname);
		ArmorStand ntag = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

		ntag.setCustomName(name);
		ntag.setCustomNameVisible(true);
		ntag.addScoreboardTag("p:" + pname);
		ntag.setVisible(false);
		ntag.setGravity(false);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				ntag.teleport(player.getLocation());
			}
		}, 0, 5);
	}

	public Player getPlayer(String player) {
		for(Player ps : Bukkit.getOnlinePlayers()){
			if (ps.getName().equalsIgnoreCase(player)) {
				return ps;
			}
		}

		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender,
			Command command,
			String label,
			String[] args) {

		if (command.getName().equalsIgnoreCase("cchat")) {
			for(Player p : Bukkit.getOnlinePlayers()){
				p.sendMessage(ChatColor.BOLD + "Announcement: " + String.join(" ", args).replaceAll("_Player_", p.getName()));
			}
			return true;
		}
		else if (command.getName().equalsIgnoreCase("nick")) {
			Player player = (Player) sender;
			player.setCustomName(args[0]);
			player.setDisplayName(args[0]);
			player.setCustomNameVisible(true);
			getConfig().set("players." + player.getName() + ".name", args[0].replaceAll("&", "§"));
			if (args.length == 2) {
				getConfig().set("players." + player.getName() + ".suffix", " " + args[1].replaceAll("&", "§") + " ");
				if (args[1].equals("None")) {
					getConfig().set("players." + player.getName() + ".suffix", " ");
				}
			}
			saveConfig();
			if (args.length == 2) {
				player.sendMessage("Your nickname is now " + ChatColor.LIGHT_PURPLE + args[0].replaceAll("&", "§") + ChatColor.WHITE + " with a rank of " + ChatColor.DARK_AQUA + args[1].replaceAll("&", "§"));
			}
			else {
				player.sendMessage("Your nickname is now " + ChatColor.LIGHT_PURPLE + args[0].replaceAll("&", "§"));
			}

			return true;
		}
		else if (command.getName().equalsIgnoreCase("fly")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.setAllowFlight(!player.getAllowFlight());
				if (player.getAllowFlight() == true) {
					player.sendMessage(ChatColor.GREEN + "Flight is now active");
				}
				else {
					player.sendMessage(ChatColor.RED + "Flight is now inactive");
				}
			}
			else {
				if (Float.parseFloat(args[0]) <= 10 && Float.parseFloat(args[0]) >= -10) {
					player.setAllowFlight(true);
					player.setFlySpeed(Float.parseFloat(args[0]) / 10);
					player.sendMessage(ChatColor.GREEN + "Your flight speed is now " + args[0]);
				}
				else if (Float.parseFloat(args[0]) >= 11) {
					player.sendMessage("The number you have entered (" + args[0] + ") is too big, it must be at most 10");
				}

				else if (Float.parseFloat(args[0]) <= -11) {
					player.sendMessage("The number you have entered (" + args[0] + ") is too small, it must be at least 10");
				}
			}
			return true;
		}
		else if (command.getName().equalsIgnoreCase("heal")) {
			Player player = (Player) sender;
			player.setHealth(20);
			player.sendMessage(ChatColor.GREEN + "You have been healed");
			return true;
		}
		else if (command.getName().equalsIgnoreCase("eat")) {
			Player player = (Player) sender;
			player.setFoodLevel(20);
			player.sendMessage(ChatColor.GREEN + "You have been fed");
			return true;
		}
		else if (command.getName().equalsIgnoreCase("healthy")) {
			Player player = (Player) sender;
			player.setHealth(20);
			player.setFoodLevel(20);
			player.sendMessage(ChatColor.GREEN + "You have been healed and fed");
			return true;
		}
		else if (command.getName().equalsIgnoreCase("warp")) {
			Player player = (Player) sender;
			if (args.length != 0) {
				//System.out.println(args[0]);
				if (args[0].equals("add")) {
					if (player.isOp()) {
						if (getConfig().get("warps." + args[1]) == null) {
							player.sendMessage(ChatColor.GREEN + "The warp \'" + args[1] + "\' has successfully been added");
						}
						else {
							player.sendMessage(ChatColor.GREEN + "The warp \'" + args[1] + "\' has successfully been modified");
						}
						getConfig().set("warps." + args[1] + ".x", player.getLocation().getX());
						getConfig().set("warps." + args[1] + ".y", player.getLocation().getY());
						getConfig().set("warps." + args[1] + ".z", player.getLocation().getZ());

						getConfig().set("warps." + args[1] + ".world", player.getWorld().getName());

						saveConfig();
					}

					else {
						player.sendMessage(ChatColor.RED + "You do not have permission to add warps");
					}
				}

				else if (args[0].equals("remove")) {
					if (player.isOp()) {
						if (getConfig().get("warps." + args[1]) != null) {
							getConfig().set("warps." + args[1], null);
							saveConfig();
							player.sendMessage(ChatColor.GREEN + "The warp \'" + args[1] + "\' has successfully been removed");
						}
						else {
							player.sendMessage(ChatColor.RED + "The warp \'" + args[1] + "\' does not exist, and therefore cannot be removed");
						}
					}

					else {
						player.sendMessage(ChatColor.RED + "You do not have permission to remove warps");
					}
				}

				else {
					//System.out.println("warps." + args[0]);
					if (getConfig().get("warps." + args[0]) != null) {
						//System.out.println(getConfig().getString("warps." + args[1] + ".world"));
						Location loc = new Location(Bukkit.getWorld(getConfig().getString("warps." + args[0] + ".world")), getConfig().getDouble("warps." + args[0] + ".x"), getConfig().getDouble("warps." + args[0] + ".y"), getConfig().getDouble("warps." + args[0] + ".z"));
						player.teleport(loc);
					}

					else {
						player.sendMessage(ChatColor.RED + "The warp \'" + args[0] + "\' does not exist");
					}
				}
			}

			return true;
		}

		else if (command.getName().equalsIgnoreCase("cloak")) {
			Player player = (Player) sender;
			System.out.println(player.canSee(player));
			if (!cloaked.contains(player)) {
				player.hidePlayer(player);
				for(Player ps : Bukkit.getServer().getOnlinePlayers()){
					ps.hidePlayer(player);
				}
				player.sendMessage(ChatColor.GREEN + "You are now cloaked");
				cloaked.add(player);
				getConfig().set("players." + player.getName() + ".cloaked", "yes");
			}
			else {
				for(Player ps : Bukkit.getOnlinePlayers()){
					ps.showPlayer(player);
				}
				player.sendMessage(ChatColor.RED + "You are now uncloaked");
				getConfig().set("players." + player.getName() + ".cloaked", "no");
				cloaked.remove(player);
				player.showPlayer(player);
			}
			saveConfig();
			return true;
		}

		else if (command.getName().equalsIgnoreCase("uncloak")) {
			Player player = (Player) sender;
			for(Player ps : cloaked){
				if (ps.getName().equals(args[0])) {
					player.showPlayer(ps);
					player.sendMessage(ChatColor.GREEN + "You can now see " + ChatColor.LIGHT_PURPLE + args[0]);
					return true;
				}
			}

			player.sendMessage(ChatColor.RED + "The player " + ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " is either not on the server or not cloaked");
			return true;
		}

		else if (command.getName().equalsIgnoreCase("list")) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GRAY + "Player list:");
			for(Player ps : Bukkit.getServer().getOnlinePlayers()){
				if (ps.isOp()) {
					if (!cloaked.contains(ps)) {
						player.sendMessage(ChatColor.LIGHT_PURPLE + getConfig().getString("players." + ps.getName() + ".name") + ChatColor.DARK_AQUA + getConfig().getString("players." + ps.getName() + ".suffix"));
					}
					else {
						player.sendMessage(ChatColor.LIGHT_PURPLE + getConfig().getString("players." + ps.getName() + ".name") + ChatColor.DARK_AQUA + getConfig().getString("players." + ps.getName() + ".suffix") + ChatColor.BLUE + "(Cloaked)");
					}
				}
				else {
					player.sendMessage(ChatColor.LIGHT_PURPLE + ps.getCustomName());
				}
			}
			return true;
		}

		else if (command.getName().equalsIgnoreCase("docommand")) {
			Player player = (Player) sender;

			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(args[0]), args[1].replace("_", " "));
			return true;
		}

		else if (command.getName().equalsIgnoreCase("invsee")) {
			Player player = (Player) sender;
			if (args.length == 1) {
				Player t = getPlayer(args[0]);
				PlayerInventory pi = t.getInventory();
				player.openInventory(pi);
			}
			else {
				player.sendMessage(ChatColor.RED + "Usage: /invsee <player>");
			}
			return true;
		}

		else if (command.getName().equalsIgnoreCase("mute")) {
			Player player = (Player) sender;
			getConfig().set("players." + args[0] + ".muted", "yes");
			player.sendMessage(ChatColor.GREEN + "Muted " + args[0]);
			return true;
		}

		else if (command.getName().equalsIgnoreCase("unmute")) {
			Player player = (Player) sender;
			getConfig().set("players." + args[0] + ".muted", "no");
			player.sendMessage(ChatColor.GREEN + "Unmuted " + args[0]);
			return true;
		}

		else if (command.getName().equalsIgnoreCase("mutelist")) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GRAY + "Muted players list:");
			for(Player ps : Bukkit.getServer().getOnlinePlayers()) {
				if (getConfig().get("players." + ps.getName() + ".muted") == "yes") {
					if (ps.isOp()) {
						player.sendMessage(ChatColor.LIGHT_PURPLE + getConfig().getString("players." + ps.getName() + ".name") + ChatColor.DARK_AQUA + getConfig().getString("players." + ps.getName() + ".suffix"));
					}
					else {
						player.sendMessage(ChatColor.LIGHT_PURPLE + ps.getCustomName());
					}
				}
			}
			return true;
		}

		else if (command.getName().equalsIgnoreCase("holo")) {
			Player player = (Player) sender;
			//createHologram(player.getLocation(), String.join(" ", args));
			//new Hologram(String.join(" ", args), player.getLocation());
			ItemStack a = new ItemStack(Material.DIAMOND_SPADE);
        	a.setDurability((short) 1);
        	ItemMeta am = a.getItemMeta();
        	am.setUnbreakable(true);
        	a.setItemMeta(am);
        	player.getInventory().addItem(a);
			return true;
			//CameraUtil.goToNearestCamera(player);
		}

		else if (command.getName().equalsIgnoreCase("kit")) {
			Player player = (Player) sender;
			if (args[0].equalsIgnoreCase("bw")) {
				/*ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        		LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
        		helmMeta.setColor(Color.BLACK);
        		helm.setItemMeta(helmMeta);*/

				ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
				chestMeta.setColor(Color.BLACK);
				chestMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Vest");
				chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);

				chest.setItemMeta(chestMeta);

				/*ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
        		LeatherArmorMeta legMeta = (LeatherArmorMeta) leg.getItemMeta();
        		legMeta.setColor(Color.BLACK);
        		leg.setItemMeta(legMeta);

        		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        		chestMeta.setColor(Color.BLACK);
        		boots.setItemMeta(bootsMeta);*/

				//player.getInventory().addItem(helm);
				player.getInventory().addItem(chest);
				//player.getInventory().addItem(leg);
				//player.getInventory().addItem(boots);
			}
			return true;
		}

		else if (command.getName().equalsIgnoreCase("worlds")) {
			Player player = (Player) sender;
			if (args[0].equals("add")) {
				getServer().createWorld(new WorldCreator(args[1]));
			}
			else if (args[0].equals("goto")) {

				try {
					Location loc = player.getLocation();
					if (args[1].equals("world")) {
						loc.setWorld(Bukkit.getWorld(args[1]));
						loc.setX(904);
						loc.setY(58);
						loc.setZ(-326);
					}

					else {
						loc.setWorld(Bukkit.getWorld(args[1]));
						loc.setX(0);
						loc.setY(100);
						loc.setZ(0);
					}
					player.teleport(loc);
				}

				catch (Exception e) {
					player.sendMessage(ChatColor.RED + "The world '" + args[1] + "' does not exist");
				}
			}
			return true;
		}

		else if (command.getName().equalsIgnoreCase("game")) {
			Player player = (Player) sender;
			if (args[0].equals("join")) {
				if (args[1].equalsIgnoreCase("blockwars") || (args[1].equalsIgnoreCase("blockwarz"))) {
					if (args[2].equalsIgnoreCase("red") || args[2].equalsIgnoreCase("blue")) {
						inBlockWars.put(player, args[2]);
						player.sendMessage(ChatColor.GREEN + "You have joined BlockWarz and are on the " + args[2].toLowerCase() + " team");
						ItemStack selector = new ItemStack(Material.COMPASS);
						ItemMeta selecm = selector.getItemMeta();
						selecm.setDisplayName("&f&lWeapons Selector".replace('&', '§'));
						selector.setItemMeta(selecm);
						player.getInventory().setItem(4, selector);


						ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
						LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
						helmMeta.setColor(Color.BLACK);
						helmMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Helmet");
						helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);

						helm.setItemMeta(helmMeta);

						ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
						LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
						chestMeta.setColor(Color.BLACK);
						chestMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Vest");
						chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);

						chest.setItemMeta(chestMeta);

						ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
						LeatherArmorMeta legMeta = (LeatherArmorMeta) leg.getItemMeta();
						legMeta.setColor(Color.BLACK);
						legMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Leggings");
						legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);

						leg.setItemMeta(legMeta);

						ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
						LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
						bootsMeta.setColor(Color.BLACK);
						bootsMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Boots");
						bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
						bootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 5, true);

						boots.setItemMeta(bootsMeta);

						ItemStack shield = new ItemStack(Material.SHIELD);

						ItemMeta sMeta = shield.getItemMeta();
						BlockStateMeta bMeta = (BlockStateMeta) sMeta;
						bMeta.addEnchant(Enchantment.DURABILITY, 3, true);
						bMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
						Banner banner = (Banner) bMeta.getBlockState();
						banner.setBaseColor(DyeColor.BLACK);
						bMeta.setBlockState(banner);
						shield.setItemMeta(bMeta);

						player.getInventory().setHelmet(helm);
						player.getInventory().setChestplate(chest);
						player.getInventory().setLeggings(leg);
						player.getInventory().setBoots(boots);
						player.getInventory().setItemInOffHand(shield);
					}
					else {
						player.sendMessage(ChatColor.RED + "Argument 3 has to be either red or blue");
					}
				}
			}

			else if (args[0].equals("leave")) {
				inBlockWars.remove(player);
				player.sendMessage(ChatColor.RED + "You have left the game you were in");
			}

			return true;
		}

		else if (command.getName().equalsIgnoreCase("rank")) {
			//getPlayer(args[0]).sendMessage("a");
			if (args[0].equalsIgnoreCase("reset") && getConfig().get("players." + ((Player) sender).getName() + ".rankpath") != null) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank " + ((Player) sender).getName() + " " + getConfig().get("players." + ((Player) sender).getName() + ".rankargs"));
				return true;
			}
			else if (sender instanceof Player) {
				return true;
			}
			if (args[1].equalsIgnoreCase("admin")) {
				if (args[2].equalsIgnoreCase("clear")) {
					getPlayer(args[0]).setOp(false);

					PermissionAttachment attachment = getPlayer(args[0]).addAttachment(this);

					for (PermissionAttachmentInfo pio : getPlayer(args[0]).getEffectivePermissions()) {
						attachment.setPermission(pio.getPermission(), false);
						//getPlayer(args[0]).sendMessage(pio.getPermission());
					}
				}

				else if (args[2].equalsIgnoreCase("trusted")) {
					getPlayer(args[0]).setOp(true);
					PermissionAttachment attachment = getPlayer(args[0]).addAttachment(this);

					//attachment.setPermission("worldedit.*", false);
					attachment.setPermission("minecraft.command.stop", false);
					attachment.setPermission("bukkit.command.reload", false);
					attachment.setPermission("minecraft.command.kick", false);
					attachment.setPermission("minecraft.command.ban", false);
					attachment.setPermission("minecraft.command.banip", false);
					
					
					attachment.setPermission("superiorcraft.editworld", true);
					attachment.setPermission("superiorcraft.addmin", false);
					//attachment.setPermission("superiorcraft.rank", false);
					//attachment.unsetPermission("superiorcraft.rank");
					//getPlayer(args[0]).sendMessage("a");

					getConfig().set("players." + args[0] + ".name", ("&6&l" + getPlayer(args[0]).getName() + "&r").replaceAll("&", "§"));
					getConfig().set("players." + args[0] + ".suffix", " " + "&c&lTrusted&r".replaceAll("&", "§") + " ");
				}

				else if (args[2].equalsIgnoreCase("cocoa")) {
					getPlayer(args[0]).setOp(true);

					PermissionAttachment attachment = getPlayer(args[0]).addAttachment(this);
					attachment.setPermission("minecraft.command.stop", false);

					for (PermissionAttachmentInfo pio : getPlayer(args[0]).getEffectivePermissions()) {
						attachment.setPermission(pio.getPermission(), true);
						//getPlayer(args[0]).sendMessage(pio.getPermission());
					}

					getConfig().set("players." + args[0] + ".name", ("&6&l" + getPlayer(args[0]).getName() + "&r").replaceAll("&", "§"));
					getConfig().set("players." + args[0] + ".suffix", " " + "&r&kA&r&6&l&nCo-Owner&r&kA&r".replaceAll("&", "§") + " ");
				}

				else if (args[2].equalsIgnoreCase("owner")) {
					getPlayer(args[0]).setOp(true);

					PermissionAttachment attachment = getPlayer(args[0]).addAttachment(this);

					for (PermissionAttachmentInfo pio : getPlayer(args[0]).getEffectivePermissions()) {
						attachment.setPermission(pio.getPermission(), true);
						//getPlayer(args[0]).sendMessage(pio.getPermission());
					}

					getConfig().set("players." + args[0] + ".name", ("&0&kA&5&l" + getPlayer(args[0]).getName() + "&0&kA&r").replaceAll("&", "§"));
					getConfig().set("players." + args[0] + ".suffix", " " + "&0&kA&a&lOwner&0&kA&r".replaceAll("&", "§") + " ");
				}

				else {
					sender.sendMessage(ChatColor.RED + "Error: Rank not found!");
					return true;
				}
			}

			else {
				sender.sendMessage(ChatColor.RED + "Error: Rank not found!");
				return true;
			}

			sender.sendMessage(ChatColor.GREEN + args[0] + " is now ranked as a " + args[1].toLowerCase() + "." + args[2].toLowerCase());
			getConfig().set("players." + args[0] + ".rankpath", args[1].toLowerCase() + "." + args[2].toLowerCase());
			getConfig().set("players." + args[0] + ".rankargs", args[1].toLowerCase() + " " + args[2].toLowerCase());
			saveConfig();

			return true;
		}

		else if (command.getName().equalsIgnoreCase("velo")) {
			Player player = (Player) sender;

			if (args.length != 0) {
				player.setVelocity(player.getVelocity().multiply(Double.parseDouble(args[0])));
				player.sendMessage(ChatColor.GREEN + "Velocity Multiplied by " + args[0]);
			}

			else {
				player.setVelocity(player.getVelocity().multiply(3.0));
				player.sendMessage(ChatColor.GREEN + "Velocity Multiplied by 3");
			}

			player.setFlying(false);
			player.setGliding(true);

			return true;
		}

		else if (command.getName().equals("cp")) {
			Player player = (Player) sender;
			int tf = 0;
			int ttf = 0;

			Menu m = new Menu("Flags", 9);

			for (Entity e : player.getWorld().getEntities()) {
				if (e.getName().equals("flag")) {
					ttf++;
					BlockState bs = e.getLocation().getBlock().getState();
					Banner bm = (Banner) bs;
					if (bm.getBaseColor() == DyeColor.valueOf(inBlockWars.get(player).toUpperCase())) {
						tf++;
					}
					ItemStack wool = new ItemStack(Material.WOOL, 1, bm.getBaseColor().getWoolData());

					ItemMeta woolm = wool.getItemMeta();

					//player.sendMessage(String.valueOf(e.getLocation().getX()));
					//woolm.getLore().add("A");
					ArrayList<String> wlore = new ArrayList<String>();
					wlore.add(String.valueOf(e.getLocation().getX()));
					wlore.add(String.valueOf(e.getLocation().getY()));
					wlore.add(String.valueOf(e.getLocation().getZ()));

					woolm.setLore(wlore);
					wool.setItemMeta(woolm);

					m.inv.addItem(wool);
				}
			}
			
			player.sendMessage(ChatColor.GRAY + "The " + ChatColor.valueOf(inBlockWars.get(player).toUpperCase()) + inBlockWars.get(player) + " team" + ChatColor.GRAY + " has " + tf + " out of " + ttf + " flags in there possession");
			player.openInventory(m.inv);

			return true;
		}

		else if (command.getName().equalsIgnoreCase("echest")) {
			Player player = (Player) sender;

			player.openInventory(getPlayer(args[0]).getEnderChest());

			return true;
		}

		if (command.getName().equalsIgnoreCase("gui")) {
			Player player = (Player) sender;

			if (args[0].equalsIgnoreCase("getblock")) {
				Menu m = new Menu("Blocks", 27);

				for (CustomBlock cbl : CustomBlock.blocks) {
					ItemStack it = cbl.getItem();
					ItemMeta itm = it.getItemMeta();

					itm.setDisplayName(cbl.getName());

					it.setItemMeta(itm);

					if (!cbl.getName().equalsIgnoreCase("blockloader")) m.inv.addItem(it);
				}

				player.openInventory(m.inv);
			}
			
			if (args[0].equalsIgnoreCase("panels")) {
				Menu m = new Menu("Panels", 27);

				for (CustomPanel pan : CustomPanel.panels) {
					ItemStack it = pan.getTexture().getTextureItem();
					m.inv.addItem(it);
				}

				player.openInventory(m.inv);
			}

			return true;
		}
		
		else if (command.getName().equalsIgnoreCase("camera")) {
			Player player = (Player) sender;
			//System.out.println(CommandConstruct.match(args, new String[] {"goto", "string"}));
			if (CommandConstruct.match(args, new String[] {"list"})) {
				for (ArmorStand entity : player.getWorld().getEntitiesByClass(ArmorStand.class)) {
					if (entity.getCustomName() != null && entity.getCustomName().equals("Camera")) {
						//player.sendMessage(entity.getLocation().toString() + " : " + entity.getUniqueId().toString().split("-")[0]);
						JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage(entity.getLocation().toString() + " : ", "green", "", "light_purple", ""), new JsonMessage(entity.getUniqueId().toString().split("-")[0], "green", "This is this camera's id", "light_purple", "/camera goto " + entity.getUniqueId().toString().split("-")[0])});
					}
				}
				
			} 
			else if (CommandConstruct.match(args, new String[] {"goto", "string"})) {
				//System.out.println(args[1]);
				for (ArmorStand entity : player.getWorld().getEntitiesByClass(ArmorStand.class)) {
					if (entity.getCustomName() != null && entity.getCustomName().equals("Camera") && entity.getUniqueId().toString().split("-")[0].equals(args[1])) {
						CameraUtil.goToCameraLocation(player, entity.getLocation());
					}
				}
			}
			else if (CommandConstruct.match(args, new String[] {"stop"})) {
				if (player.getScoreboardTags().contains("viewingCamera")) {
					player.removeScoreboardTag("viewingCamera");
				}
			}
			
			return true;
		}
		
		else if (command.getName().equalsIgnoreCase("spy")) {
			Player player = (Player) sender;
			if (CommandConstruct.match(args, new String[] {"info", "player"})) {
				new MultilineMessage(new String[] {
					"Player Name: " + getPlayer(args[1]).getDisplayName(),
					"Player IP: " + getPlayer(args[1]).getAddress().getHostName()
				}).setBaseColor(ChatColor.GRAY).setLinePrefix("[ESPionage] ").sendMessage(player);
			}
			
			return true;
		}
		
		//else if (command.getName().equalsIgnoreCase(""))

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender,
			Command command,
			String label,
			String[] args) {
		if (command.getName().equalsIgnoreCase("worlds")) {
			if (args[0].equals("goto")) {
				ArrayList<String> auto = new ArrayList<String>();
				for (World world : Bukkit.getWorlds()) {
					if (world.getName().startsWith(args[1])) {
						auto.add(world.getName());
						//sender.sendMessage(world.getName());
					}
				}

				return auto;
			}
			ArrayList<String> auto = new ArrayList<String>();
			ArrayList<String> posible = new ArrayList<String>();

			posible.add("goto");
			posible.add("add");
			posible.add("remove");

			for (String check : posible) {
				if (check.startsWith(args[0])) {
					auto.add(check);
					//sender.sendMessage(world.getName());
				}
			}

			return auto;
		}

		if (command.getName().equalsIgnoreCase("warp")) {
			ArrayList<String> auto = new ArrayList<String>();
			ArrayList<String> posible = new ArrayList<String>();

			posible.add("add");
			posible.add("remove");
			//posible.add("remove");

			for (String check : posible) {
				if (check.startsWith(args[0])) {
					auto.add(check);
					//sender.sendMessage(world.getName());
				}
			}

			return auto;
		}

		ArrayList<String> auto = new ArrayList<String>();

		return auto;
	}


}