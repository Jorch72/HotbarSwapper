package vazkii.hotbar

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.PreInit
import cpw.mods.fml.common.registry.TickRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.common.event.FMLPreInitializationEvent

@Mod(modid = "HotbarSwapper", name = "Hotbar Swapper", version = "1.0", modLanguage = "scala")
object HotbarSwapper {

	@PreInit
	def preInit(event : FMLPreInitializationEvent) = {
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT)
	}
	
}