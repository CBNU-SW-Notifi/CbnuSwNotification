import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinInitIosKt.doInitKoinIos(appComponent: IosApplicationComponent(networkHelper: IosNetworkHelper()))
     }
	var body: some Scene {
		WindowGroup {
			ContentView()
                .background(Color.white)
		}
	}
}
