import os
import sys

# コマンドライン引数からバージョンを取得
if len(sys.argv) != 2:
    print("Usage: python get_entity_id.py <version>")
    sys.exit(1)

version = sys.argv[1]
version_path = version.replace('.', '_')

# Minecraftのリソースパスを指定
minecraft_resource_path = f"./{version}/assets/minecraft/textures/entity"

# ディレクトリ名を格納するリスト
directory_names = []

# ディレクトリの探索
for d in os.listdir(minecraft_resource_path):
    if os.path.isdir(os.path.join(minecraft_resource_path, d)):
        directory_names.append(d)

# アルファベット順にソート
directory_names = sorted(directory_names)

# 出力ファイル名を指定
output_file = f'./entity_{version_path}.py'

# entity.pyファイルに書き出し
with open(output_file, 'w', encoding='utf-8') as f:
    f.write(f'# {version}\n')
    for name in directory_names:
        constant_name = name.upper().replace(' ', '_')
        f.write(f'{constant_name} = "{name}"\n')

print(f"ディレクトリ名のリストが{output_file}に保存されました。")
