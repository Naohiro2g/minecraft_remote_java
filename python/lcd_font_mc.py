import yaml
from param_MCJE import blocks
import param_MCJE as param

DARK_GRAY = (40, 40, 40)
GRAY = (80, 80, 80)
RED = (255, 0, 0)
GREEN = (10, 250, 10)
YELLOW = (250, 250, 20)
WHITE = (250, 250, 250)


class DotmatrixDisplay:
    def __init__(self, mc, config_file=None, config_section=None, font_file="font.txt"):
        self.mc = mc
        # デフォルト値
        self.color_on = blocks.SEA_LANTERN
        self.color_off = blocks.AIR
        self.x_org = 2
        self.y_org = 8
        self.z_org = 5
        self.col_intv = 6

        # 設定ファイルの読み込み
        if config_file:
            self.load_config(config_file, config_section)
        # 外部フォントファイルの読み込み
        self.load_font(font_file)

    def load_config(self, config_file, section=None):
        try:
            with open(config_file, "r", encoding="utf-8") as f:
                config = yaml.safe_load(f)
            if not config or "line_display" not in config:
                print("設定ファイルに line_display セクションがありません")
                return

            display_config = config["line_display"]
            if section and section in display_config:
                cfg = display_config[section]
                # 位置設定
                if "position" in cfg:
                    pos = cfg["position"]
                    if "x_org" in pos:
                        self.x_org = pos["x_org"]
                    if "y_org" in pos:
                        self.y_org = param.Y_SEA + pos["y_org"]
                    if "z_org" in pos:
                        self.z_org = pos["z_org"]
                    if "col_intv" in pos:
                        self.col_intv = pos["col_intv"]
        except Exception as e:
            print(f"設定ファイルの読み込みエラー: {e}")

    def load_font(self, font_file="font.txt"):
        try:
            with open(font_file, encoding="utf-8") as f:
                # 改行ごとに全行を読み込み
                lines = f.read().splitlines()
            # 各文字は 7 行で構成されると仮定
            n = 7
            self.font_style = [lines[i : i + n] for i in range(0, len(lines), n)]
        except Exception as e:
            print(f"フォントファイルの読み込みエラー: {e}")
            self.font_style = None

    def init_col(self, color_on=blocks.SEA_LANTERN, color_off=blocks.AIR):
        self.color_on = color_on
        self.color_off = color_off

    def init_row(self, x_org=2, y_org=8, z_org=5, col_intv=6):
        self.x_org = x_org
        self.y_org = y_org
        self.z_org = z_org
        self.col_intv = col_intv

    def update_col(self, col=0, code=2):
        if not self.font_style:
            print("フォントスタイルが読み込まれていません。")
            return

        try:
            style = self.font_style[int(code)]
        except IndexError:
            print("指定されたコードのフォントが存在しません")
            return

        # style は 7 行 5文字ずつの文字列リストです
        for r, row in enumerate(style):
            for c, ch in enumerate(row):
                color = self.color_on if ch == "1" else self.color_off
                x0 = self.x_org + self.col_intv * col + c
                y0 = self.y_org - r
                z0 = self.z_org
                self.mc.setBlock(x0, y0, z0, color)
