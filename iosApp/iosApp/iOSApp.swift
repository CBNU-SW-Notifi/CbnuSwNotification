import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinCommonKt.doInitKoin(
            appComponent: IosApplicationComponent(
                networkHelper: IosNetworkHelper()
            )
        )
     }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
